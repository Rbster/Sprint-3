package ru.sber.qa

import io.mockk.*
import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*

internal class CertificateRequestTest {
    // given
    private val certificateType = mockk<CertificateType>(relaxed = true)
    private val employeeNumber = 123L
    private val certificateRequest = CertificateRequest(employeeNumber, certificateType)
    private val hrEmployeeNumber = 12L
    private val data = byteArrayOfInts(0xFA, 0xCE)



    @Test
    fun process() {
        // given
        mockkObject(Scanner)
        mockkConstructor(Certificate::class)
        every { Scanner.getScanData() } returns data

        // when
        val certificate = certificateRequest.process(hrEmployeeNumber)

        // then
        assertEquals(data, certificate.data)
        assertEquals(hrEmployeeNumber, certificate.processedBy)
        assertEquals(certificateRequest, certificate.certificateRequest)

        unmockkAll()
    }

    @Test
    fun getEmployeeNumber() {
        // then
        assertEquals(employeeNumber, certificateRequest.employeeNumber)
    }

    @Test
    fun getCertificateType() {
        // then
        assertEquals(certificateType, certificateRequest.certificateType)
    }
}