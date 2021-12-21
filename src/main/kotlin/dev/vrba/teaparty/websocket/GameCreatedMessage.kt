package dev.vrba.teaparty.websocket

import dev.vrba.teaparty.dto.GameDto

data class GameCreatedMessage(val game: GameDto) {
    val type: String = MessageType.gameCreated
}