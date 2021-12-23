package dev.vrba.teaparty.service

import dev.vrba.teaparty.domain.Game
import dev.vrba.teaparty.domain.Lobby
import dev.vrba.teaparty.domain.Player
import dev.vrba.teaparty.domain.game.GameRound
import dev.vrba.teaparty.exceptions.GameNotFoundException
import dev.vrba.teaparty.exceptions.NotJoinedInGameException
import dev.vrba.teaparty.repository.GamesRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.Instant
import java.util.*

@Service
class GamesService(private val repository: GamesRepository) {

    fun deleteAllGames() = repository.deleteAll()

    fun createGame(lobby: Lobby): Game {
        val scores = lobby.players.associate { it.id to 0 }

        return Game(mode = lobby.mode, players = lobby.players, scores = scores).let {
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

    fun createNewRound(game: Game): GameRound {
        // TODO: Add proper list of syllables / procedural generator
        val syllable = listOf("syl", "sys", "cot").random()
        // TODO: Add game configuration later
        val end = Instant.now() + Duration.ofSeconds(15)

        return GameRound(end = end, mode = game.mode, syllable = syllable, listOf())
    }
}