package ru.matrix.registrationwithfaceapp.service.impl

import org.apache.tomcat.util.security.MD5Encoder
import org.springframework.stereotype.Service
import org.springframework.util.DigestUtils
import ru.matrix.registrationwithfaceapp.dto.RegistrationApiDto
import ru.matrix.registrationwithfaceapp.model.User
import ru.matrix.registrationwithfaceapp.repository.UserRepository
import ru.matrix.registrationwithfaceapp.service.RegistrationService
import java.util.UUID

@Service
class RegistrationServiceImpl(
    private val userRepository: UserRepository
) : RegistrationService {

    override fun startRegistration(registrationData: RegistrationApiDto) {
        if (userRepository.existsUserByUsername(registrationData.username)) return
        val newUser = User(
            id = UUID.randomUUID(),
            username = registrationData.username,
            password = DigestUtils.md5DigestAsHex(registrationData.password.toByteArray()),
            isImageLoad = false,
            userImageId = null
        )
        userRepository.save(newUser)
    }

    override fun getUsersList(): List<User> {
        return userRepository.findAll()
    }
}