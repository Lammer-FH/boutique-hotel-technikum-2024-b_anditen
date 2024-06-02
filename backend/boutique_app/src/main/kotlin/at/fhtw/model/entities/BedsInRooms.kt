package at.fhtw.model.entities

import jakarta.persistence.*

@Embeddable
class BedsInRoomsId(
    val bedTypeId: Long, val roomId: Long
)

@Entity
class BedsInRooms(
    @EmbeddedId val id: BedsInRoomsId,

    @ManyToOne @MapsId("bedTypeId") val bedType: BedType,
    @ManyToOne @MapsId("roomId") val room: Room,
    @Column(nullable = false) val amount: Int
)
