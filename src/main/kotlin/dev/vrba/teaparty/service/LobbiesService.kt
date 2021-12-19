package dev.vrba.teaparty.service

import dev.vrba.teaparty.domain.Lobby
import dev.vrba.teaparty.domain.Player
import dev.vrba.teaparty.domain.game.GameMode
import dev.vrba.teaparty.repository.LobbiesRepository
import org.springframework.stereotype.Service

@Service
class LobbiesService(private val repository: LobbiesRepository) {

    fun listAll(): List<Lobby> = repository.findAll().toList()

    fun createLobby(mode: GameMode, owner: Player): Lobby =
        Lobby(mode, owner).let { repository.save(it) }

}