package at.fhtw.model.repositories

import at.fhtw.model.entities.Room
import org.springframework.data.repository.CrudRepository

interface RoomRepository: CrudRepository<Room, Int>