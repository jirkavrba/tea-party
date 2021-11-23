package dev.vrba.teaparty.controllers

import dev.vrba.teaparty.engine.GameMode
import dev.vrba.teaparty.entities.Lobby
import dev.vrba.teaparty.entities.Player
import dev.vrba.teaparty.repositories.LobbiesRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@Validated
@RestController
@RequestMapping("/api")
class LobbiesController(private val repository: LobbiesRepository) {

    @GetMapping("/lobbies")
    fun listLobbies(): ResponseEntity<*> {
        val lobbies = repository.findAll().toList()
        val response = mapOf(
                "count" to lobbies.size,
                "lobbies" to lobbies
        )

        return ResponseEntity.ok(response)
    }

    // TODO: Add support for lobby configuration
    data class CreateLobbyRequest(@NotBlank val mode: String)

    @PostMapping("/lobby/create")
    fun createLobby(@AuthenticationPrincipal host: Player, @Valid @RequestBody request: CreateLobbyRequest): ResponseEntity<*> {
        val mode = when (request.mode) {
            "green" -> GameMode.GreenTea
            "yellow" -> GameMode.YellowTea
            "red" -> GameMode.RedTea
            else -> throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid game mode selected.")
        }

        val lobby = Lobby(mode = mode, host = host)
        val instance = repository.save(lobby)

        return ResponseEntity.ok(instance)
    }
}