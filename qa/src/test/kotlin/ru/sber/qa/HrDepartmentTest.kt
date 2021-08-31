package ru.sber.qa

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import java.time.Clock
import java.time.DayOfWeek
import java.time.LocalDateTime
import kotlin.random.Random

internal class HrDepartmentTest {
    private val certificateRequest : CertificateRequest = mockk<CertificateRequest>()
    private val certificate: Certificate = mockk<Certificate>()
    private val hrEmployeeNumber: Long = 21L


    @Test
    fun receiveRequest_doesNotThrowOnOddDays() {
        // given
        every { certificateRequest.certificateType } returns CertificateType.LABOUR_BOOK

        // then
        when(LocalDateTime.now(Clock.systemUTC()).dayOfWeek) {
            in listOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY) -> org.junit.jupiter.api.assertThrows<NotAllowReceiveRequestException> { HrDepartment.receiveRequest(certificateRequest) }
            in listOf(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY) -> assertDoesNotThrow { HrDepartment.receiveRequest(certificateRequest) }
            in listOf(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY) -> org.junit.jupiter.api.assertThrows<WeekendDayException> { HrDepartment.receiveRequest(certificateRequest) }
        }

    }

    @Test
    fun receiveRequest_doesNotThrowOnEvenDays() {
        // given
        every { certificateRequest.certificateType } returns CertificateType.NDFL

        // then
        when(LocalDateTime.now(Clock.systemUTC()).dayOfWeek) {
            in listOf(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY) -> org.junit.jupiter.api.assertThrows<NotAllowReceiveRequestException> { HrDepartment.receiveRequest(certificateRequest) }
            in listOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY) -> assertDoesNotThrow { HrDepartment.receiveRequest(certificateRequest) }
            in listOf(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY) -> org.junit.jupiter.api.assertThrows<WeekendDayException> { HrDepartment.receiveRequest(certificateRequest) }
        }

    }

    @Test
    fun processNextRequest_doesNotThrowOnEvenDays() {
        // given
        every { certificateRequest.certificateType } returns CertificateType.NDFL
        every { certificateRequest.process(hrEmployeeNumber) } returns certificate

        if (LocalDateTime.now(Clock.systemUTC()).dayOfWeek in listOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY)) {
                // when
                HrDepartment.receiveRequest(certificateRequest)
                // then
                assertDoesNotThrow { HrDepartment.processNextRequest(21) }

        } else {
            // then
            org.junit.jupiter.api.assertThrows<Exception> { HrDepartment.processNextRequest(21) }
        }

    }

    @Test
    fun processNextRequest_doesNotThrowOnOddDays() {
        // given
        every { certificateRequest.certificateType } returns CertificateType.LABOUR_BOOK
        every { certificateRequest.process(hrEmployeeNumber) } returns certificate

        if (LocalDateTime.now(Clock.systemUTC()).dayOfWeek in listOf(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY)) {
            // when
            HrDepartment.receiveRequest(certificateRequest)
            // then
            assertDoesNotThrow { HrDepartment.processNextRequest(hrEmployeeNumber) }

        } else {
            // then
            org.junit.jupiter.api.assertThrows<Exception> { HrDepartment.processNextRequest(21) }
        }

    }


}