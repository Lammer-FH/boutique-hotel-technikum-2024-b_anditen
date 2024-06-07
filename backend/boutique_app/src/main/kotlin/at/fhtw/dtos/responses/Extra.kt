package at.fhtw.dtos.responses

data class Extra(
    val name: String,
) {
    companion object {
        fun from(old: at.fhtw.model.entities.RoomExtra): Extra {
            return Extra(old.name)
        }
    }
}
