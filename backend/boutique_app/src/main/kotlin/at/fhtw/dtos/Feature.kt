package at.fhtw.dtos

data class Feature(
    val name: String,
) {
    companion object {
        fun from(old: at.fhtw.model.entities.RoomFeature): Feature {
            return Feature(old.name)
        }
    }
}
