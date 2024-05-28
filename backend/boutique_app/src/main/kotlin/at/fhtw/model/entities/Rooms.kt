package at.fhtw.model.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table


@Entity
@Table
data class Rooms(
    @Id @GeneratedValue @Column val id: Int,
    @OneToOne @JoinColumn val types: RoomTypes,
    @ManyToMany @JoinTable val beds: Set<BedTypes>
)