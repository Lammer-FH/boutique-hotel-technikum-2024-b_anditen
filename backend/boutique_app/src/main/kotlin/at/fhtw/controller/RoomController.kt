package at.fhtw.controller

import at.fhtw.dtos.responses.Room
import at.fhtw.model.repositories.RoomRepository
import org.apache.commons.io.IOUtils
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.io.InputStream
import java.sql.Date
import java.time.LocalDate

@RequestMapping("/rooms")
@RestController
@CrossOrigin(origins = ["http://localhost:8100"])
class RoomController(val roomRepository: RoomRepository) {

    @GetMapping()
    fun getAllRooms(): Iterable<Room> = roomRepository.findAll().map { Room.from(it) }

    @GetMapping("/{id}")
    fun getRoomById(@PathVariable id: Long): Room = roomRepository.findById(id).map { Room.from(it) }.orElseThrow() { RoomNotFoundException(id)}

    @GetMapping("/{id}/available")
    fun isRoomAvailable(
        @PathVariable id: Long,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) start: LocalDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) end: LocalDate
    ): Boolean =
        roomRepository.checkIfRoomIsAvailable(id, Date.valueOf(start), Date.valueOf(end))

    @ResponseBody
    @GetMapping(value = ["/{id}/image"], produces = [MediaType.IMAGE_JPEG_VALUE])
    fun getImageWithMediaType(@PathVariable id: String): ByteArray {
        val imagePath = "/images/rooms/hotel_room_$id.jpg"
        val `in`: InputStream = this::class.java.getResourceAsStream(imagePath)
            ?: throw ImageNotFoundException(id)
        return IOUtils.toByteArray(`in`)
    }
}

class ImageNotFoundException(val id: String) : RuntimeException()