package ru.sber.oop

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class TownSquareTest {
    val townSquare = TownSquare()
    @Test
    fun load() {
        assertEquals("No one would prefer to live here", townSquare.load())
    }

    @Test
    fun getName() {
        assertEquals("Town Square", townSquare.name)
    }
}