package dev.vrba.teaparty.websocket.messages


object MessageType {
    const val scoredWordSubmitted = "scored-word-submitted"
    const val gameRoundUpdated = "game-round-updated"
    const val gameCreated = "game-created"
    const val lobbiesUpdated = "lobbies-updated"
    const val lobbyUpdated = "lobby-updated"
}

abstract class TypedMessage(val type: String)