package dev.vrba.teaparty.controllers

import dev.vrba.teaparty.entities.User
import dev.vrba.teaparty.repositories.UsersRepository
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

@Validated
@RestController
@RequestMapping("/api/login")
class LoginController(private val repository: UsersRepository) {

    data class LoginRequest(@NotBlank @Min(3) val username: String)

    @PostMapping
    fun login(@Valid @RequestBody request: LoginRequest): ResponseEntity<User> {
        val username = request.username
        val user = repository.save(User(username))

        return ResponseEntity.ok(user)
    }
}