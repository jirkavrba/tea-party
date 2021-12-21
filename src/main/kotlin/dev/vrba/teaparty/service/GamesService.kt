package dev.vrba.teaparty.service

import dev.vrba.teaparty.domain.Game
import dev.vrba.teaparty.domain.Lobby
import dev.vrba.teaparty.repository.GamesRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class GamesService(private val repository: GamesRepository) {

    fun createGame(lobby: Lobby): Game {
        return Game(mode = lobby.mode, players = lobby.players).let {
            repository.save(it)
        }
    }
}