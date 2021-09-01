package ru.sber.qa

import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CertificateTest {
    // given
    private val certificateRequest = mockk<CertificateRequest>(relaxed = true)
    private val processedBy = 123L
    private val data = byteArrayOfInts(0xFA, 0xCE)
    private val certificate = Certificate(certificateRequest, processedBy, data)

    @Test
    fun getCertificateRequest() {
        // then
        assertEquals(certificateRequest, certificate.certificateRequest)
    }

    @Test
    fun getProcessedBy() {
        // then
        assertEquals(processedBy, certificate.processedBy)
    }

    @Test
    fun getData() {
        // then
        assertArrayEquals(data, certificate.data)
    }
}