package ru.matrix.registrationwithfaceapp.service

import ru.matrix.registrationwithfaceapp.dto.RegistrationApiDto
import ru.matrix.registrationwithfaceapp.model.User

interface RegistrationService {
    fun startRegistration(registrationData: RegistrationApiDto)
    fun getUsersList(): List<User>
}