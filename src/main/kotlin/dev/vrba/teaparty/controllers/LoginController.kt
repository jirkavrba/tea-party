package dev.vrba.teaparty.controllers

import dev.vrba.teaparty.entities.Player
import dev.vrba.teaparty.repositories.PlayersRepository
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

@Validated
@RestController
@RequestMapping("/api/login")
class LoginController(private val repository: PlayersRepository) {

    data class LoginRequest(@NotBlank @Min(3) val username: String)

    @PostMapping
    fun login(@Valid @RequestBody request: LoginRequest): ResponseEntity<Player> {
        val username = request.username
        val player = Player(username = username)

        return ResponseEntity.ok(repository.save(player))
    }

    @GetMapping("/check")
    fun check(@AuthenticationPrincipal player: Player): ResponseEntity<*> {
        return ResponseEntity.ok(mapOf(
            "message" to "Logged in as ${player.username}"
        ))
    }
}