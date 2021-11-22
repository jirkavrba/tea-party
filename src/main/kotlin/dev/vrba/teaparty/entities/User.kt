package dev.vrba.teaparty.entities

import org.hibernate.Hibernate
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

private fun generateRandomToken(): String {
    val pool = ('0' .. '9') + ('a' .. 'z') + ('A' .. 'Z') + listOf('~', '-', '+', '_')
    val seed = List(32) { pool.random() }

    return seed.joinToString("")
}

@Entity
data class User(
    @Id
    val id: UUID,
    val username: String,
    val token: String
) {
    constructor(username: String) : this(UUID.randomUUID(), username, generateRandomToken())
    constructor() : this("")

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        if (other !is User) return false

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    override fun toString(): String = this::class.simpleName + "(id = $id)"
}
