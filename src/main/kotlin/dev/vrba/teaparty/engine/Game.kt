package dev.vrba.teaparty.engine

import dev.vrba.teaparty.entities.Player
import java.util.*

fun createGame(players: List<Player>): Game =
        Game(
            phase = GamePhase.Starting,
            players = players,
            points =  players.associate { it.id to 0 }
        )

class Game(
    val id: UUID = UUID.randomUUID(),
    val phase: GamePhase,
    val players: List<Player>,
    val points: Map<UUID, Int>,

    // TODO: Add support for game rounds
)