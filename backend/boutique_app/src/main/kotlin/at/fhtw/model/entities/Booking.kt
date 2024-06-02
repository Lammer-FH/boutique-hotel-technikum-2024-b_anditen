package at.fhtw.model.entities

import jakarta.persistence.*
import jakarta.validation.constraints.Min
import org.aspectj.lang.annotation.Before
import org.checkerframework.checker.units.qual.C
import org.checkerframework.common.value.qual.IntRange
import org.hibernate.annotations.Check
import org.hibernate.annotations.Checks
import org.hibernate.annotations.GenericGenerator
import org.jetbrains.annotations.NotNull
import java.sql.Date

@Entity
@Table()
class Booking(
    @Column(nullable = false) val customer: Customer,
    @Column(nullable = false) @Min(1) val numberOfGuests: Int,
    @Checks(Check(constraints = "start < end")) @Column(nullable = false) val start: Date,
    @Column(nullable = false) val end: Date,
    @OneToMany() val rooms: Set<Room>,
    @Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "native") @GenericGenerator(
        name = "native", strategy = "native"
    ) val id: Long? = null,
)

@Embeddable
class Customer(
    @Column(nullable = false) val firstName: String,
    @Column(nullable = false) val lastName: String,
    @Column(nullable = false) val email: String,
    @Column(nullable = false) val phoneNumber: String,
    @Column(nullable = false) val birthDate: Date
)
