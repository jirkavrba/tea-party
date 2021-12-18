package dev.vrba.teaparty.domain

import dev.vrba.teaparty.security.SecurityUtils.generateRandomToken
import org.hibernate.Hibernate
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Player(
    @Id
    val id: UUID,
    val username: String,
    val token: String,
) {
    constructor(username: String) : this(UUID.randomUUID(), username, generateRandomToken())

    // Methods optimized for hibernate performance

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        if (other !is Player) return false

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    override fun toString(): String = this::class.simpleName + "(id = $id)"
}
