package ru.sber.qa

import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CertificateRequestTest {
    private val certificateType : CertificateType = mockk()
    private val employeeNumber = 123L
    private val certificateRequest = CertificateRequest(employeeNumber, certificateType)


    @Test
    fun process() {

    }

    @Test
    fun getEmployeeNumber() {
        assertEquals(employeeNumber, certificateRequest.employeeNumber)
    }

    @Test
    fun getCertificateType() {
        assertEquals(certificateType, certificateRequest.certificateType)
    }
}