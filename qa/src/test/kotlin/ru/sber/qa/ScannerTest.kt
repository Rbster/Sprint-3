package ru.sber.qa

import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.random.Random

internal class ScannerTest {
    @BeforeEach
    fun setUp() {
        mockkObject(Random)
    }

    @AfterEach
    fun unMock() {
        unmockkAll()
    }

    @Test
    fun getScanData_throws() {
        // given
        every { Random.nextLong(5000L, 15000L) } returns Scanner.SCAN_TIMEOUT_THRESHOLD + 1
        // then
        assertThrows<ScanTimeoutException> { Scanner.getScanData() }
    }

    @Test
    fun getScanData() {
        // given
        val data = byteArrayOf(1)
        every { Random.nextLong(5000L, 15000L) } returns 1
        every { Random.nextBytes(100) } returns data

        // then
        assertEquals(data, Scanner.getScanData())
    }
}