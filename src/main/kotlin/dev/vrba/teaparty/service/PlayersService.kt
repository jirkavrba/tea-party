package dev.vrba.teaparty.service

import dev.vrba.teaparty.domain.Player
import dev.vrba.teaparty.repository.PlayersRepository
import org.springframework.stereotype.Service

@Service
class PlayersService(private val repository: PlayersRepository) {

    fun createPlayerAccount(username: String): Player {
        return Player(username).let {
            repository.save(it)
        }
    }

    fun deleteAllPlayerAccounts() {
        repository.deleteAll()
    }

}