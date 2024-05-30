package at.fhtw.model.entities

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class RoomFeature(
    @Id val feature: String,
    val name: String,
)
