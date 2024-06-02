package at.fhtw.controller

import at.fhtw.dtos.requests.CreateBookingRequest
import at.fhtw.model.entities.BedsInRoomsId
import at.fhtw.model.entities.Booking
import at.fhtw.model.repositories.BookingsRepository
import at.fhtw.model.repositories.RoomRepository
import jakarta.validation.constraints.NotEmpty
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Validated
@RequestMapping("/bookings")
@RestController
class BookingsController(val bookingsRepository: BookingsRepository, val roomRepository: RoomRepository) {

    @PostMapping
    fun createBooking(@RequestBody createBookingRequest: CreateBookingRequest) {
        val booking = Booking(
            start = createBookingRequest.startDate,
            end = createBookingRequest.endDate,
            customer = createBookingRequest.customer.toEntity(),
            numberOfGuests = createBookingRequest.numberOfGuests,
            rooms = createBookingRequest.roomIds.map { roomRepository.findById(it).orElseThrow() }.toSet()
        )

        val unavailable = createBookingRequest.roomIds.filter {
            !roomRepository.checkIfRoomIsAvailable(
                it,
                booking.start,
                booking.end
            )
        }
        if (unavailable.isNotEmpty()) {
            throw RoomUnavailableException(unavailable.toSet())
        }

        // Todo: Check if rooms are available
        bookingsRepository.save(booking)
    }
}

class RoomUnavailableException(val roomIds: Set<Long>) : RuntimeException()