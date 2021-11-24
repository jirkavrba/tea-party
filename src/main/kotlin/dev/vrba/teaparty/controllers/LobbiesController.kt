package dev.vrba.teaparty.controllers

import dev.vrba.teaparty.engine.GameMode
import dev.vrba.teaparty.entities.Lobby
import dev.vrba.teaparty.entities.Player
import dev.vrba.teaparty.repositories.LobbiesRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@Validated
@RestController
@RequestMapping("/api")
class LobbiesController(private val repository: LobbiesRepository) {

    data class LobbyListingDto(val id: UUID, val mode: GameMode, val players: Int)

    @GetMapping("/lobbies")
    fun lobbies(): ResponseEntity<List<LobbyListingDto>> {
        val lobbies = repository.findAll()
                .toList()
                .map { LobbyListingDto(it.id, it.mode, it.players.size + 1) }

        return ResponseEntity.ok(lobbies)
    }

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

    @GetMapping("/lobby/{id}")
    fun lobby(@PathVariable id: UUID): ResponseEntity<Lobby> {
        val lobby = repository.findByIdOrNull(id)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Lobby could not be found")

        return ResponseEntity.ok(lobby)
    }

    @PostMapping("/lobby/{id}/join")
    fun join(@AuthenticationPrincipal player: Player, @PathVariable id: UUID): ResponseEntity<*> {
        val lobby = repository.findByIdOrNull(id)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Lobby could not be found")

        if (lobby.players.contains(player)) {
            throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Player is already joined in this lobby")
        }

        lobby.players.add(player)
        repository.save(lobby)

        // TODO: Broadcast change to websocket sessions

        return ResponseEntity.ok().build<String>()
    }

    @PostMapping("/lobby/{id}/leave")
    fun leave(@AuthenticationPrincipal player: Player, @PathVariable id: UUID): ResponseEntity<*> {
        val lobby = repository.findByIdOrNull(id)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Lobby could not be found")

        if (!lobby.players.contains(player)) {
            throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Player is not joined in this lobby")
        }

        lobby.players.remove(player)
        repository.save(lobby)

        // TODO: Broadcast change to websocket sessions

        return ResponseEntity.ok().build<String>()
    }

    @PostMapping("/lobby/{id}/start")
    fun start(@AuthenticationPrincipal player: Player, @PathVariable id: UUID): ResponseEntity<*> {
        val lobby = repository.findByIdOrNull(id)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Lobby could not be found")

        if (lobby.host != player) {
            throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Only the host can start the game")
        }

        // TODO: Start the game
        // TODO: Broadcast change to websocket connections

        return ResponseEntity.ok().build<String>()
    }
}