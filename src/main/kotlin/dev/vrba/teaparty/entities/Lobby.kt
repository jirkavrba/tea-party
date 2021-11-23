package dev.vrba.teaparty.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import dev.vrba.teaparty.engine.GameMode
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.OneToOne

@Entity
class Lobby(
    @Id
    val id: UUID = UUID.randomUUID(),

    val mode: GameMode,

    @OneToOne
    @JsonIgnoreProperties("token")
    val host: Player,

    @OneToMany
    @JsonIgnoreProperties("token")
    val players: MutableList<Player> = mutableListOf()
)