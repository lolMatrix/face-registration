package ru.matrix.registrationwithfaceapp.model

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class User(
    @Id
    val id: UUID,

    val username: String,
    val password: String,
    var isImageLoad: Boolean,
    var userImageId: UUID?
) {
    @ManyToOne
    @JoinColumn(name = "userImageId", insertable = false, updatable = false)
    lateinit var userImage: File
}
