package ru.sber.qa

import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import kotlin.random.Random

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CertificateRequestTest {
    // given
    private val certificateType : CertificateType = mockk<CertificateType>(relaxed = true)
    private val employeeNumber = 123L
    private val certificateRequest = CertificateRequest(employeeNumber, certificateType)
    private val hrEmployeeNumber = 12L

    private val seed: Long = 123L

    @BeforeAll
    fun setOn() {
        var Random = Random(seed)
    }

    @Test
    fun getRandom() {
        println(Random(seed).nextLong(5000L, 15000L))
        println(Random(seed).nextLong(5000L, 15000L))

    }


    @Test
    fun process() {
        // then
        assertDoesNotThrow { certificateRequest.process(hrEmployeeNumber) }
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