package at.fhtw.dtos

import at.fhtw.model.entities.BedType

data class Bed(
    val name: String,
    val size: Int,
    val amount: Int,
) {
    companion object {
        fun from(old: BedType, amount: Int): Bed {
            return Bed(old.name, old.size, amount)
        }
    }
}
