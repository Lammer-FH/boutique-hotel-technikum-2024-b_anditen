package at.fhtw.dtos

import org.springframework.format.annotation.DateTimeFormat
import java.sql.Date


data class Customer(
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) val birthDate: Date
) {
    companion object {
        fun from(old: at.fhtw.model.entities.Customer): Customer {
            return Customer(
                old.firstName, old.lastName, old.email, old.phoneNumber, old.birthDate
            )
        }
    }

    fun toEntity(): at.fhtw.model.entities.Customer {
        return at.fhtw.model.entities.Customer(
            firstName, lastName, email, phoneNumber, birthDate
        )
    }
}