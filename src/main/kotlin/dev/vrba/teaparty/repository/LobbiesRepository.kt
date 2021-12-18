package dev.vrba.teaparty.repository

import dev.vrba.teaparty.domain.Lobby
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface LobbiesRepository : CrudRepository<Lobby, UUID>