package ru.sber.qa

import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import kotlin.random.Random

internal class ScannerTest {
    @Test
    fun getScanData_throws() {
        // given
        mockkObject(Random)
        every { Random.nextLong(5000L, 15000L) } returns Scanner.SCAN_TIMEOUT_THRESHOLD + 1
        // then
        assertThrows<ScanTimeoutException> { Scanner.getScanData() }
        unmockkAll()
    }

    @Test
    fun getScanData_doesNotThrow() {
        // given
        mockkObject(Random)
        val data = byteArrayOf(1)
        every { Random.nextLong(5000L, 15000L) } returns 1
        every { Random.nextBytes(100) } returns data

        // then
        assertEquals(data, Scanner.getScanData())
        unmockkAll()
    }
}