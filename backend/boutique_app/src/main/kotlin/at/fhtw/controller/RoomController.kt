package at.fhtw.controller


import at.fhtw.dtos.Room
import at.fhtw.model.repositories.RoomRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/rooms")
@RestController
class RoomController(val roomRepository: RoomRepository) {

    @GetMapping("")
    fun getAllRooms(): Iterable<Room> = roomRepository.findAll().map { Room.from(it) }

    @GetMapping("/{id}")
    fun getRoomById(@PathVariable id: Long): Room = roomRepository.findById(id).map { Room.from(it) }.orElseThrow()
}