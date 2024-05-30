package at.fhtw.model.entities

import jakarta.persistence.*

@Embeddable
data class BedsInRoomId(
    val bedType: String,
    val roomId: Long
)

@Entity
data class BedsInRoom(
    @EmbeddedId val id: BedsInRoomId,
    val count: Int,
    @ManyToOne @MapsId("roomId") val room: Room,
    @ManyToOne @MapsId("bedType") val bed: BedType
)