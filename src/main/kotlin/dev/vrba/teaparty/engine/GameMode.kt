package dev.vrba.teaparty.engine

/**
 * Enum representing a game mode for lobby. The selected mode affects, how a game round is scored:
 *
 * - green tea  - all that matters is speed, first player to submit a valid word receives 5 points,
 *                second receives 3 points, third 1 point and the rest receive 0 points.
 *
 * - yellow tea - this one is about quantity, players need to submit comma-separated words, which are
 *                then counted. Player with the largest number of valid words receives 5 points.
 *                If there is a tie, player who submitted the words first, receives the points.
 *
 * - red tea    - players need to come up with the longest valid word possible. The player who submits
 *                the longest valid word, receives 5 points. If there is a tie, the player who submitted
 *                his word first, receives the points.
 */
enum class GameMode {
    GreenTea,
    YellowTea,
    RedTea
}