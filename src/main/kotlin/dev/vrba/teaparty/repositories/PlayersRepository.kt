package dev.vrba.teaparty.repositories

import dev.vrba.teaparty.entities.Player
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PlayersRepository : CrudRepository<Player, UUID> {
    fun findByToken(token: String): Player?
}