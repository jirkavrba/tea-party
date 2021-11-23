package dev.vrba.teaparty.controllers

import dev.vrba.teaparty.engine.GameMode
import dev.vrba.teaparty.entities.Player
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@Validated
@RestController
@RequestMapping("/api/lobby")
class LobbiesController {

    // TODO: Add support for lobby configuration
    data class CreateLobbyRequest(@NotBlank val mode: String)

    @PostMapping("/create")
    fun createLobby(@AuthenticationPrincipal host: Player, @Valid @RequestBody request: CreateLobbyRequest): ResponseEntity<*> {
        val mode = when (request.mode) {
            "green" -> GameMode.GreenTea
            "yellow" -> GameMode.YellowTea
            "red" -> GameMode.RedTea
            // TODO: Use the errors api instead of custom body
            else -> return ResponseEntity.badRequest().body(
                    mapOf(
                            "error" to HttpStatus.BAD_REQUEST.reasonPhrase,
                            "status" to HttpStatus.BAD_REQUEST.value(),
                            "message" to "Invalid game mode selected, allowed values are: 'green', 'yellow' or 'red'"
                    )
            )
        }

        // TODO: Implement lobbies
        val lobby = "new instance of lobby(${mode})"

        return ResponseEntity.ok(lobby)
    }
}