package ru.sber.qa

import io.mockk.*
import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*
import java.time.*


internal class HrDepartmentTest {
    private val certificateRequest = mockk<CertificateRequest>()
    private val certificate = mockk<Certificate>()
    private val hrEmployeeNumber: Long = 21L

    @BeforeEach
    fun setUp() {
        mockkObject(CertificateType.LABOUR_BOOK)
        mockkObject(CertificateType.NDFL)
    }

    @AfterEach
    fun unMock() {
        unmockkAll()
    }

    @Test
    fun receiveRequest_throwsOnWeekend() {
        // given
        // Saturday
        HrDepartment.clock = Clock.fixed(
            Instant.parse("2021-09-04T10:00:00Z")
            , ZoneOffset.UTC)
        every { certificateRequest.certificateType } returns CertificateType.LABOUR_BOOK

        // then
        assertThrows<WeekendDayException> { HrDepartment.receiveRequest(certificateRequest) }


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
        assertThrows<NotAllowReceiveRequestException> { HrDepartment.receiveRequest(certificateRequest) }
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
        assertThrows<NotAllowReceiveRequestException> { HrDepartment.receiveRequest(certificateRequest) }

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