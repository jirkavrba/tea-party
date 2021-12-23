package dev.vrba.teaparty.domain.game

import java.time.Instant

data class GameRound(
    val end: Instant,
    val mode: GameMode,
    val words: List<ScoredWord>,
)