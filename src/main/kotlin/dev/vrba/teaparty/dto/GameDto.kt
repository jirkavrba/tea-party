package dev.vrba.teaparty.dto

import dev.vrba.teaparty.domain.Game
import dev.vrba.teaparty.domain.game.GameMode
import java.util.*

fun Game.dto(): GameDto = GameDto(
    id,
    mode,
    players.map { it.dto() }
)

data class GameDto(
    val id: UUID,
    val mode: GameMode,
    val players: List<PlayerDto>
)