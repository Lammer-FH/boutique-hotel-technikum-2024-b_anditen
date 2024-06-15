package at.fhtw.model.repositories

import at.fhtw.model.entities.Booking
import org.springframework.data.repository.CrudRepository

interface BookingsRepository : CrudRepository<Booking, Int>