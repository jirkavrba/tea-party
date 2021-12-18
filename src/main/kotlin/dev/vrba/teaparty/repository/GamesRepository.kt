package dev.vrba.teaparty.repository

import dev.vrba.teaparty.domain.Game
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface GamesRepository : CrudRepository<Game, UUID>