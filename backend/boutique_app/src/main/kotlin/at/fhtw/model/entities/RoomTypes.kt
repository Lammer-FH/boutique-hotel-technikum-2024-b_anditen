package at.fhtw.model.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.springframework.context.annotation.Lazy

@Entity
@Table
data class RoomTypes(
    @Id @Column val type: String,
    @Column val name: String,
    @Column val description: String,
    @OneToMany @Lazy val rooms: Set<Rooms>
)