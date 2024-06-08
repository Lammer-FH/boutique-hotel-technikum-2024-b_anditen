package at.fhtw.model.entities

import jakarta.persistence.*
import jakarta.validation.constraints.Min
import org.hibernate.annotations.Check
import org.hibernate.annotations.Checks
import org.hibernate.annotations.GenericGenerator
import java.sql.Date

@Entity
@Table
class Booking(
    @Column(nullable = false) val customer: Customer,
    @Column(nullable = false) @Min(1) val numberOfGuests: Int,
    @Checks(Check(constraints = "start < end")) @Column(nullable = false) @Temporal(TemporalType.DATE) val start: Date,
    @Column(nullable = false) @Temporal(TemporalType.DATE) val end: Date,
    @ManyToMany val rooms: Set<Room>,
    @Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "native") @GenericGenerator(
        name = "native", strategy = "native"
    ) val id: Long? = null,
    @Column(nullable = false) val breakfast: Boolean
)

@Embeddable
class Customer(
    @Column(nullable = false) val firstName: String,
    @Column(nullable = false) val lastName: String,
    @Column(nullable = false) val email: String,
    @Column(nullable = false) val phoneNumber: String,
    @Column(nullable = false) val birthDate: Date
)
