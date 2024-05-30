package at.fhtw.model.entities

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity
@Table
data class
Room(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native") val id: Long,
    val name: String,
    val description: String,
    @OneToMany val features: Set<RoomFeature>,
    @OneToMany val beds: List<BedType>,
)