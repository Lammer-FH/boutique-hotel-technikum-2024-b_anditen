package at.fhtw.controller

import at.fhtw.dtos.responses.Room
import at.fhtw.model.repositories.RoomRepository
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.sql.Date
import java.time.LocalDate

@RequestMapping("/rooms")
@RestController
class RoomController(val roomRepository: RoomRepository) {

    @GetMapping()
    fun getAllRooms(): Iterable<Room> = roomRepository.findAll().map { Room.from(it) }

    @GetMapping("/{id}")
    fun getRoomById(@PathVariable id: Long): Room = roomRepository.findById(id).map { Room.from(it) }.orElseThrow()

    @GetMapping("/{id}/available")
    fun isRoomAvailable(
        @PathVariable id: Long,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) start: LocalDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) end: LocalDate
    ): Boolean =
        roomRepository.checkIfRoomIsAvailable(id, Date.valueOf(start), Date.valueOf(end))
}