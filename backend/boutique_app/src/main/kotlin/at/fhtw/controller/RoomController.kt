package at.fhtw.controller

import at.fhtw.model.entities.Room
import at.fhtw.model.repositories.RoomRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/room/")
@RestController
class RoomController(val roomRepository: RoomRepository) {

    data class RoomResponse(val id: Int, val type: String, val description: String, val beds: Set<String>,)

    @GetMapping("all")
    fun getAllRooms(): Iterable<Room> = roomRepository.findAll()
}