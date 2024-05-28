package at.fhtw.model.repositories

import at.fhtw.model.entities.Rooms
import org.springframework.data.repository.CrudRepository

interface RoomRepository: CrudRepository<Rooms, Int>