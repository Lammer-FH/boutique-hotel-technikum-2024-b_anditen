package at.fhtw.model.entities

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity
class BedType(
    @Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "native") @GenericGenerator(
        name = "native",
        strategy = "native"
    ) val id: Long,
    val name: String,
    val size: Int,
)