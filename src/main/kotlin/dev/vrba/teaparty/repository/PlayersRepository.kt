package dev.vrba.teaparty.repository

import dev.vrba.teaparty.domain.Player
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PlayersRepository : CrudRepository<Player, UUID> {

    fun findByToken(token: String): Player?

}