package at.fhtw.model.entities

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity
@Table
class Room(
    @Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "native") @GenericGenerator(
        name = "native",
        strategy = "native"
    ) val id: Long,
    val name: String,
    val pricePerNight: Double,
    val imageUrL: String?,
    @ManyToMany
    val extras: Set<RoomExtra>,
    @ManyToOne(optional = false)
    val type: RoomType,
    @OneToMany(mappedBy = "room")
    val beds: Set<BedsInRooms>,
)