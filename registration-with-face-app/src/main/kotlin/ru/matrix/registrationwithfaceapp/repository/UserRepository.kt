package ru.matrix.registrationwithfaceapp.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.matrix.registrationwithfaceapp.model.User
import java.util.UUID

interface UserRepository : JpaRepository<User, UUID> {

    fun existsUserByUsername(username: String): Boolean
}