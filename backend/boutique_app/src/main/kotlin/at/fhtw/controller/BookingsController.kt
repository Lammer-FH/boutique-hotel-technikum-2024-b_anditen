package at.fhtw.controller

import at.fhtw.dtos.requests.CreateBookingRequest
import at.fhtw.dtos.responses.BookingDto
import at.fhtw.model.entities.Booking
import at.fhtw.model.repositories.BookingsRepository
import at.fhtw.model.repositories.RoomRepository
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Validated
@RequestMapping("/bookings")
@RestController
class BookingsController(val bookingsRepository: BookingsRepository, val roomRepository: RoomRepository) {

    @PostMapping
    fun createBooking(@RequestBody createBookingRequest: CreateBookingRequest): BookingDto {
        val booking = Booking(
            start = createBookingRequest.startDate,
            end = createBookingRequest.endDate,
            customer = createBookingRequest.customer.toEntity(),
            numberOfGuests = createBookingRequest.numberOfGuests,
            breakfast = createBookingRequest.breakfast,
            rooms = createBookingRequest.roomIds.map {
                roomRepository.findById(it).orElseThrow { RoomNotFoundException(it) } }.toSet()
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

        return BookingDto.from(bookingsRepository.save(booking))
    }
}

class RoomUnavailableException(val roomIds: Set<Long>) : RuntimeException()

class RoomNotFoundException(val id: Long) : RuntimeException()