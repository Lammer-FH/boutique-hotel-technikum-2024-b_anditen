package at.fhtw.controller

import at.fhtw.model.repositories.RoomRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/room/")
@RestController
class RoomController(val roomRepository: RoomRepository) {

    @GetMapping("all")
    fun getAllRooms(): String {
        return roomRepository.findAll().joinToString()
    }
}