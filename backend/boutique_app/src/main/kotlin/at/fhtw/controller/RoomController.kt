package at.fhtw.controller

import at.fhtw.model.entities.Rooms
import at.fhtw.model.repositories.RoomRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/room/")
@RestController
class RoomController(val roomRepository: RoomRepository) {

    @GetMapping("all")
    fun getAllRooms(): Iterable<Rooms> = roomRepository.findAll()
}