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
    @ManyToMany val features: Set<RoomFeature>,
    @OneToMany(
        mappedBy = "room",
    ) val beds: Set<BedsInRooms>,
    @ManyToOne(optional = false) val type: RoomType,
    val imageUrL: String?,
)