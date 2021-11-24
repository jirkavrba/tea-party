package dev.vrba.teaparty.repositories

import dev.vrba.teaparty.entities.Game
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface GamesRepository : CrudRepository<Game, UUID>