package at.fhtw.dtos.responses

data class Room(
    val id: Long,
    val name: String,
    val pricePerNight: Double,
    val type: String,
    val description: String,
    val beds: List<Bed>,
    val extras: Set<Extra>,
    val imageUrl: String?,
    val available: Boolean? = null,
) {
    companion object {
        fun from(old: at.fhtw.model.entities.Room, available: Boolean? = null): Room {
            return Room(
                old.id,
                old.name,
                old.pricePerNight,
                old.type.name,
                old.type.description,
                old.beds.map { Bed.from(it.bedType, it.amount) },
                old.extras.plus(old.type.standardExtras).map { Extra.from(it) }.toSet(),
                old.imageUrL,
                available
            )
        }
    }
}


