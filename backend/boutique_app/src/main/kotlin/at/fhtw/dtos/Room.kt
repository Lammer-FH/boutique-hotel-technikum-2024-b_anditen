package at.fhtw.dtos

data class Room(
    val id: Long,
    val type: String,
    val description: String,
    val beds: List<Bed>,
    val features: Set<Feature>,
) {
    companion object {
        fun from(old: at.fhtw.model.entities.Room): Room {
            return Room(
                old.id,
                old.type.name,
                old.type.description,
                old.beds.map { Bed.from(it.bedType, it.amount) },
                old.features.plus(old.type.standardFeatures).map { Feature.from(it) }.toSet(),
            )
        }
    }
}


