package ru.matrix.registrationwithfaceapp.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.matrix.registrationwithfaceapp.dto.RegistrationApiDto
import ru.matrix.registrationwithfaceapp.service.RegistrationService

@RestController
@RequestMapping("/registration")
class RegistrationController(
    private val registrationService: RegistrationService
) {

    @PostMapping
    fun register(registrationData: RegistrationApiDto) = registrationService.startRegistration(registrationData)

    @GetMapping
    fun getUsers() = registrationService.getUsersList()
}