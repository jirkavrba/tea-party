package dev.vrba.teaparty.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface GamesRepository : CrudRepository<Game, UUID>