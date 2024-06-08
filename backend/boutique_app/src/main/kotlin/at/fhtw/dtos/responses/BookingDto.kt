package at.fhtw.dtos.responses

import at.fhtw.model.entities.Booking
import at.fhtw.model.entities.Customer
import java.sql.Date

data class BookingDto(
    val customer: Customer,
    val numberOfGuests: Int,
    val end: Date,
    val rooms: Set<Room>,
    val id: Long? = null,
    val breakfast: Boolean
) {
    companion object {
        fun from(old: Booking): BookingDto {
            return BookingDto(old.customer, old.numberOfGuests, old.end, old.rooms.map { Room.from(it) }.toSet(), old.id, old.breakfast)
        }
    }
}