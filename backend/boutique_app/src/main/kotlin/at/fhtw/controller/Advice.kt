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
    fun handleConflict(exception: RoomUnavailableException): ResponseEntity<String> {
        return ResponseEntity("Rooms: " + exception.roomIds.joinToString(",") + " are not available", HttpHeaders(), HttpStatus.CONFLICT)
    }
}