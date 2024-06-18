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
class RoomController(val roomRepository: RoomRepository) {

    @GetMapping()
    fun getAllRooms(
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) start: LocalDate?,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) end: LocalDate?
    ): Iterable<Room> {
        var rooms = roomRepository.findAll().map { Room.from(it) }
        if (start != null && end != null) {
            rooms = rooms.map {
                it.copy(
                    available = roomRepository.checkIfRoomIsAvailable(
                        it.id,
                        Date.valueOf(start),
                        Date.valueOf(end)
                    )
                )
            }
        }

        return rooms
    }

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