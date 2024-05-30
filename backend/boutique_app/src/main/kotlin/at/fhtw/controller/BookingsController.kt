package at.fhtw.controller

import at.fhtw.model.entities.Booking
import at.fhtw.model.repositories.BookingsRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/booking/")
@RestController()
class BookingsController(val bookingsRepository: BookingsRepository) {

    @GetMapping("all")
    fun getAllBookings(): Iterable<Booking> = bookingsRepository.findAll()
}