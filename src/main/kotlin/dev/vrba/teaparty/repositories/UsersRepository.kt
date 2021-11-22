package dev.vrba.teaparty.repositories

import dev.vrba.teaparty.entities.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UsersRepository : CrudRepository<User, UUID>