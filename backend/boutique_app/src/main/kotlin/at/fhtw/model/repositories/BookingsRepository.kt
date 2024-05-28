package at.fhtw.model.repositories

import at.fhtw.model.entities.Bookings
import org.springframework.data.repository.CrudRepository

interface BookingsRepository : CrudRepository<Bookings, Int>