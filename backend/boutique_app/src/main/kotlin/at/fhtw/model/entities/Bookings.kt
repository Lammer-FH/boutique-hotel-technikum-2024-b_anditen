package at.fhtw.model.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import java.sql.Date

@Entity
data class Bookings(
    @Id @GeneratedValue @Column val id: Int,
    @Column val startDate: Date,
    @Column val endDate: Date,
    @OneToMany val rooms: Set<Rooms>
)
