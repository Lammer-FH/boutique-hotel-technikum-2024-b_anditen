package at.fhtw.controller

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class Advice {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(RoomUnavailableException::class)
    fun handleImageNotFound(exception: RoomUnavailableException): ResponseEntity<String> {
        return ResponseEntity(
            "Rooms: " + exception.roomIds.joinToString(",") + " are not available",
            HttpHeaders(),
            HttpStatus.CONFLICT
        )
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ImageNotFoundException::class)
    fun handleImageNotFound(exception: ImageNotFoundException): ResponseEntity<String> {
        return ResponseEntity("Image id not found: " + exception.id, HttpHeaders(), HttpStatus.NOT_FOUND)
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RoomNotFoundException::class)
    fun handleRoomNotFound(exception: RoomNotFoundException): ResponseEntity<String> {
        return ResponseEntity("Room not found: " + exception.id, HttpHeaders(), HttpStatus.NOT_FOUND)
    }
}