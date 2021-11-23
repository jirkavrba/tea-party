package dev.vrba.teaparty.controllers

import dev.vrba.teaparty.engine.WordlistService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@Validated
@RestController
@RequestMapping("/validation")
class WordValidationController(private val service: WordlistService) {

    data class ValidationRequest(@NotBlank val word: String)

    @PostMapping
    fun validateWord(@Valid @RequestBody request: ValidationRequest): ResponseEntity<*> {
        val valid = service.isKnownWord(request.word)
        val response = mapOf(
                "word" to request.word,
                "valid" to valid
        )

        return ResponseEntity.ok(response)
    }
}