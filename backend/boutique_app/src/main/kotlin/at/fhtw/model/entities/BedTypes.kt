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
data class BedTypes(
    @Id @Column val type: String,
    @Column val name: String,
    @Column val description: String,
    @ManyToMany @JoinTable @Lazy private val rooms: Set<Rooms>
)