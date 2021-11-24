package dev.vrba.teaparty.entities

import dev.vrba.teaparty.engine.GameMode
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Game(
    @Id
    val id: UUID,

    val mode: GameMode,

    @OneToMany
    val players: List<Player>
)