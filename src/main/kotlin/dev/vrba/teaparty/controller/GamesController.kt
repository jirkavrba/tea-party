package dev.vrba.teaparty.controller

import dev.vrba.teaparty.domain.Player
import dev.vrba.teaparty.dto.GameDto
import dev.vrba.teaparty.dto.dto
import dev.vrba.teaparty.service.GamesService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/games")
class GamesController(private val gamesService: GamesService) {

    @GetMapping("/{id}")
    fun game(@PathVariable("id") id: UUID, @AuthenticationPrincipal player: Player): ResponseEntity<GameDto> {
        val game = gamesService.findGame(id, player)
        val dto = game.dto()

        return ResponseEntity.ok(dto)
    }
}