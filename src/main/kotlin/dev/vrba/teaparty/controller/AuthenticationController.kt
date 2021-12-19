package dev.vrba.teaparty.controller

import dev.vrba.teaparty.domain.Player
import dev.vrba.teaparty.dto.PlayerDto
import dev.vrba.teaparty.dto.dto
import dev.vrba.teaparty.service.PlayersService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
@RequestMapping("/api/authentication")
class AuthenticationController(private val playersService: PlayersService) {

    data class CreateAccountRequest(val username: String)

    data class CreateAccountResponse(val player: PlayerDto, val token: String)

    @PostMapping("/create-account")
    fun createAccount(@RequestBody request: CreateAccountRequest): ResponseEntity<CreateAccountResponse> {
        val account = playersService.createPlayerAccount(request.username)
        val response = CreateAccountResponse(
            player = account.dto(),
            token = account.token
        )

        return ResponseEntity.ok(response)
    }

    @Secured("ROLE_PLAYER")
    @PostMapping("/check")
    fun check(@AuthenticationPrincipal player: Player): ResponseEntity<PlayerDto> {
        return ResponseEntity.ok(player.dto())
    }
}