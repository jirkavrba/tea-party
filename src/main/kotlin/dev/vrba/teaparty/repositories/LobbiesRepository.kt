package dev.vrba.teaparty.repositories

import dev.vrba.teaparty.entities.Lobby
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface LobbiesRepository : CrudRepository<Lobby, UUID>