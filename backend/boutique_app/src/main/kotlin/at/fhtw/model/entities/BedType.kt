package at.fhtw.model.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import org.springframework.context.annotation.Lazy

@Entity
@Table
data class BedType(
    @Id val type: String,
    val name: String,
    val description: String,
    @ManyToMany @JoinTable private val rooms: Set<Room>
)