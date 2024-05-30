package at.fhtw.model.entities

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.annotations.Cascade

@Entity
@Table
data class
Room(
    @Id @GeneratedValue val id: Long,
    @OneToOne @JoinColumn val types: RoomType,
    @OneToMany val features: Set<RoomFeature>,
    @OneToMany(mappedBy = "room", orphanRemoval = true) val beds: Set<BedsInRoom>,
)