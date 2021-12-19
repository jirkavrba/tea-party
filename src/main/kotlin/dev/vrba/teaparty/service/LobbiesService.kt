package dev.vrba.teaparty.service

import dev.vrba.teaparty.domain.Lobby
import dev.vrba.teaparty.domain.Player
import dev.vrba.teaparty.domain.game.GameMode
import dev.vrba.teaparty.dto.dto
import dev.vrba.teaparty.exceptions.AlreadyJoinedLobbyException
import dev.vrba.teaparty.exceptions.LobbyNotFoundException
import dev.vrba.teaparty.exceptions.NotJoinedInLobbyException
import dev.vrba.teaparty.repository.LobbiesRepository
import dev.vrba.teaparty.websocket.LobbyUpdatedMessage
import org.springframework.data.repository.findByIdOrNull
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class LobbiesService(
    private val repository: LobbiesRepository,
    private val template: SimpMessagingTemplate
) {

    fun listAll(): List<Lobby> = repository.findAll().toList()

    fun createLobby(mode: GameMode, owner: Player): Lobby {
        val lobby = Lobby(mode, owner)
        return repository.save(lobby)
    }

    fun joinLobby(id: UUID, player: Player): Lobby {
        val lobby = repository.findByIdOrNull(id) ?: throw LobbyNotFoundException

        if (lobby.players.contains(player)) {
            throw AlreadyJoinedLobbyException
        }

        return lobby.copy(players = lobby.players + player).let {
            template.convertAndSend("/lobby/${it.id}", LobbyUpdatedMessage(it.dto()))
            repository.save(it)
        }
    }

    fun leaveLobby(id: UUID, player: Player): Lobby? {
        val lobby = repository.findByIdOrNull(id) ?: throw LobbyNotFoundException

        if (!lobby.players.contains(player)) {
            throw NotJoinedInLobbyException
        }

        val players = lobby.players - player

        // If there are no players in the lobby, delete it
        if (players.isEmpty()) {
            repository.delete(lobby)
            return null
        }

        // If the player is the owner of the lobby, pass the ownership to the first player instead
        val owner = if (lobby.owner != player) lobby.owner else players.first()

        return lobby.copy(owner = owner, players = players).let {
            template.convertAndSend("/lobby/${it.id}", LobbyUpdatedMessage(it.dto()))
            repository.save(it)
        }
    }

}