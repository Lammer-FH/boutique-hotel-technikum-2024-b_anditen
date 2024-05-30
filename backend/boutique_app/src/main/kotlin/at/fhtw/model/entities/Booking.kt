package at.fhtw.model.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import java.sql.Date

@Entity
data class Booking(
    @Id @GeneratedValue val id: Long,
    val startDate: Date,
    val endDate: Date,
    @OneToMany val rooms: Set<Room>
)
