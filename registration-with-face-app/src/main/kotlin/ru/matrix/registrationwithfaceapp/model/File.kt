package ru.matrix.registrationwithfaceapp.model

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class File(
    @Id
    val id: UUID,

    val content: ByteArray
)
