package dev.vrba.teaparty.websocket

import dev.vrba.teaparty.domain.Lobby
import dev.vrba.teaparty.dto.LobbyDto

data class LobbyUpdatedMessage(val lobby: LobbyDto)