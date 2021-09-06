package ru.sber.oop

import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.random.Random

internal class GoblinTest {
    val gobJonathan = Goblin("Jonathan", "Your average gob", "meanness", 500)
    val gobMathew = Goblin("Mathew", "Like waffles", "gluttony", 300)
    val roll = 300
    @BeforeEach
    fun setUp() {
        mockkObject(Random)
    }

    @AfterEach
    fun unMock() {
        unmockkAll()
    }



    @Test
    fun `Mathew attacks Jonathan`() {
        every { Random.nextInt() } returns roll
        gobMathew.attack(gobJonathan)
        assertEquals(500 - roll / 2, gobJonathan.healthPoints)
    }

    @Test
    fun `Jonathan attacks Mathew`() {
        every { Random.nextInt() } returns roll
        gobJonathan.attack(gobMathew)
        assertEquals(300 - roll / 2 , gobMathew.healthPoints)
    }
}