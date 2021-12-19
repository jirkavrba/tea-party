package dev.vrba.teaparty.controller

import dev.vrba.teaparty.domain.Player
import dev.vrba.teaparty.domain.game.GameMode
import dev.vrba.teaparty.dto.LobbyDto
import dev.vrba.teaparty.dto.dto
import dev.vrba.teaparty.service.LobbiesService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/lobbies")
class LobbiesController(private val service: LobbiesService) {

    @GetMapping
    fun listLobbies(): ResponseEntity<List<LobbyDto>> {
        val lobbies = service.listAll()
        val dtos = lobbies.map { it.dto() }

        return ResponseEntity.ok(dtos)
    }

    data class CreateLobbyRequest(val mode: GameMode)

    @PostMapping("/create")
    fun createLobby(
        @RequestBody request: CreateLobbyRequest,
        @AuthenticationPrincipal player: Player
    ): ResponseEntity<LobbyDto> {
        val lobby = service.createLobby(request.mode, player)
        val dto = lobby.dto()

        return ResponseEntity.ok(dto)
    }
}