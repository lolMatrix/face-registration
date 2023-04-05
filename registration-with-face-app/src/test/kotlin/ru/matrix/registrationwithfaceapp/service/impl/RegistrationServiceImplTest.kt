package ru.matrix.registrationwithfaceapp.service.impl

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType
import ru.matrix.registrationwithfaceapp.dto.RegistrationApiDto
import ru.matrix.registrationwithfaceapp.repository.UserRepository
import ru.matrix.registrationwithfaceapp.service.RegistrationService

@SpringBootTest
class RegistrationServiceImplTest {

    @MockkBean
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var registrationService: RegistrationService

    @Test
    fun `should persist user when username not exist`() {
        val registrationApiDto = RegistrationApiDto(
            username = "test",
            password = "test"
        )
        every { userRepository.existsUserByUsername(registrationApiDto.username) } returns false
        every { userRepository.save(any()) } returns mockk()

        registrationService.startRegistration(registrationApiDto)

        verify { userRepository.save(any()) }
    }

    @Test
    fun `should not persist user when username already exists`() {
        val registrationApiDto = RegistrationApiDto(
            username = "test",
            password = "test"
        )
        every { userRepository.existsUserByUsername(registrationApiDto.username) } returns true

        registrationService.startRegistration(registrationApiDto)

        verify(exactly = 0) { userRepository.save(any()) }
    }

    @Configuration
    @ComponentScan(
        useDefaultFilters = false,
        includeFilters = [
            ComponentScan.Filter(classes = [RegistrationService::class], type = FilterType.ASSIGNABLE_TYPE)
        ]
    )
    class RegistrationServiceImplTestConfig
}