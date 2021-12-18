package dev.vrba.teaparty.domain

import dev.vrba.teaparty.domain.game.GameMode
import org.hibernate.Hibernate
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
data class Lobby(
    @Id
    val id: UUID,

    val mode: GameMode,

    @ManyToOne
    val owner: Player,

    @OneToMany
    val players: List<Player>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        if (other !is Lobby) return false

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    override fun toString(): String = this::class.simpleName + "(id = $id)"
}
