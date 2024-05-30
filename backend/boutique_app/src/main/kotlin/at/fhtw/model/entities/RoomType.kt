package at.fhtw.model.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.springframework.context.annotation.Lazy

@Entity
@Table
data class RoomType(
    @Id val type: String,
    val name: String,
    val description: String,
    @OneToMany val rooms: Set<Room>
)