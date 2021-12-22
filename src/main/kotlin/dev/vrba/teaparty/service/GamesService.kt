package dev.vrba.teaparty.service

import dev.vrba.teaparty.domain.Game
import dev.vrba.teaparty.domain.Lobby
import dev.vrba.teaparty.domain.Player
import dev.vrba.teaparty.exceptions.GameNotFoundException
import dev.vrba.teaparty.exceptions.NotJoinedInGameException
import dev.vrba.teaparty.repository.GamesRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class GamesService(private val repository: GamesRepository) {

    fun deleteAllGames() = repository.deleteAll()

    fun createGame(lobby: Lobby): Game {
        return Game(mode = lobby.mode, players = lobby.players).let {
            repository.save(it)
        }
    }

    fun findGame(id: UUID, player: Player): Game {
        // I can imagine this being handled with monads, maybe one day...

        val game = repository.findByIdOrNull(id) ?: throw GameNotFoundException

        if (!game.players.contains(player)) {
            throw NotJoinedInGameException
        }

        return game
    }
}