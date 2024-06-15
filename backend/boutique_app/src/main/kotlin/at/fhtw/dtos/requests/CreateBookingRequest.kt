package at.fhtw.dtos.requests

import at.fhtw.dtos.Customer
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import org.springframework.format.annotation.DateTimeFormat
import java.sql.Date

data class CreateBookingRequest(
    @NotEmpty val roomIds: List<Long>,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) val startDate: Date,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) val endDate: Date,
    val customer: Customer,
    @Min(1)
    val numberOfGuests: Int,
    @NotEmpty val breakfast: Boolean
)
