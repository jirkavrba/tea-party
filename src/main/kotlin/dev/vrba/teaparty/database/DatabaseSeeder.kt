package dev.vrba.teaparty.database

import dev.vrba.teaparty.engine.GameMode
import dev.vrba.teaparty.entities.Lobby
import dev.vrba.teaparty.entities.Player
import dev.vrba.teaparty.repositories.LobbiesRepository
import dev.vrba.teaparty.repositories.PlayersRepository
import org.springframework.stereotype.Component

@Component
class DatabaseSeeder(
    private val playersRepository: PlayersRepository,
    private val lobbiesRepository: LobbiesRepository
) {
    fun run() {
        val player = playersRepository.save(Player(username = "Tea Party bot"))
        val lobbies = listOf(GameMode.GreenTea, GameMode.GreenTea, GameMode.YellowTea, GameMode.RedTea, GameMode.YellowTea)
                .map { Lobby(mode = it, host = player) }
                .let { lobbiesRepository.saveAll(it) }

    }
}
