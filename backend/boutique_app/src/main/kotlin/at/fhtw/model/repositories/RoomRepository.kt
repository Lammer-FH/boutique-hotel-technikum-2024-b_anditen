package at.fhtw.model.repositories

import at.fhtw.model.entities.Room
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.sql.Date

interface RoomRepository : CrudRepository<Room, Long> {

    @Query(nativeQuery = true, value =
        """SELECT IF((SELECT 1
              FROM booking_rooms
                       join hoteldb.booking b on b.id = booking_rooms.booking_id
              WHERE rooms_id = :roomId
                AND ((b.start <= DATE(:start) and b.end >= DATE(:end)) OR
                     b.start between DATE(:start) and DATE(:end)
                  OR b.end between DATE(:start) and DATE(:end)
                  )),'false','true');"""
    )
    fun checkIfRoomIsAvailable(
        @Param("roomId") roomId: Long, @Param("start") start: Date, @Param("end") end: Date
    ): Boolean
}