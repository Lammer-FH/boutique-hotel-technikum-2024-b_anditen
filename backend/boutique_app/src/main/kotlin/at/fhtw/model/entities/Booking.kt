package at.fhtw.model.entities

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.sql.Date

@Entity
data class Booking(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native") val id: Long,
    val startDate: Date,
    val endDate: Date,
    @OneToMany val rooms: Set<Room>
)
