package ru.sber.qa

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import java.time.*
import kotlin.random.Random

internal class HrDepartmentTest {
    private val certificateRequest : CertificateRequest = mockk<CertificateRequest>()
    private val certificate: Certificate = mockk<Certificate>()
    private val hrEmployeeNumber: Long = 21L

    @Test
    fun receiveRequest_throwsOnWeekend() {
        // given
        // Saturday
        HrDepartment.clock = Clock.fixed(
            Instant.parse("2021-09-04T10:00:00Z")
            , ZoneOffset.UTC)
        every { certificateRequest.certificateType } returns CertificateType.LABOUR_BOOK

        // then
            org.junit.jupiter.api.assertThrows<WeekendDayException> { HrDepartment.receiveRequest(certificateRequest) }
    }

    @Test
    fun receiveRequest_throwsOnOddDays() {
        // given
        // Wednesday
        HrDepartment.clock = Clock.fixed(
            Instant.parse("2021-09-01T10:00:00Z")
            , ZoneOffset.UTC)
        every { certificateRequest.certificateType } returns CertificateType.LABOUR_BOOK

        // then
        org.junit.jupiter.api.assertThrows<NotAllowReceiveRequestException> { HrDepartment.receiveRequest(certificateRequest) }
    }



    @Test
    fun receiveRequest_throwsOnEvenDays() {
        // given
        // Tuesday
        HrDepartment.clock = Clock.fixed(
            Instant.parse("2021-08-31T10:00:00Z")
            , ZoneOffset.UTC)
        every { certificateRequest.certificateType } returns CertificateType.NDFL

        // then
        org.junit.jupiter.api.assertThrows<NotAllowReceiveRequestException> { HrDepartment.receiveRequest(certificateRequest) }

    }

    @Test
    fun receiveRequest_doesNotThrowOnOddDays() {
        // given
        // Tuesday
        HrDepartment.clock = Clock.fixed(
            Instant.parse("2021-08-31T10:00:00Z")
            , ZoneOffset.UTC)
        every { certificateRequest.certificateType } returns CertificateType.LABOUR_BOOK

        // then
        assertDoesNotThrow { HrDepartment.receiveRequest(certificateRequest) }


    }

    @Test
    fun receiveRequest_doesNotThrowOnEvenDays() {
        // given
        // Wednesday
        HrDepartment.clock = Clock.fixed(
            Instant.parse("2021-09-01T10:00:00Z")
            , ZoneOffset.UTC)
        every { certificateRequest.certificateType } returns CertificateType.NDFL

        // then
        assertDoesNotThrow { HrDepartment.receiveRequest(certificateRequest) }
    }

    @Test
    fun processNextRequest_doesNotThrowOnEvenDays() {
        // given
        HrDepartment.clock = Clock.fixed(
            Instant.parse("2021-09-01T10:00:00Z")
            , ZoneOffset.UTC)
        every { certificateRequest.certificateType } returns CertificateType.NDFL
        every { certificateRequest.process(hrEmployeeNumber) } returns certificate

        // when
        HrDepartment.receiveRequest(certificateRequest)

        // then
        assertDoesNotThrow { HrDepartment.processNextRequest(21) }
    }



}