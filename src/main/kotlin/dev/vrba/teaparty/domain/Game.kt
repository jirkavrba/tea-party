package dev.vrba.teaparty.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("games")
data class Game(
    @Id
    val id: UUID,

    @DBRef
    val players: List<Player>
)