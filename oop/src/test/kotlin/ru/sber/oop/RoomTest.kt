package ru.sber.oop

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class RoomTest {
    val room = Room(name = "A")

    @Test
    fun description() {

    }

    @Test
    fun load() {

    }

    @Test
    fun getMonster() {
        assertEquals("Bob",room.monster.name)
        assertEquals( "Little green bro",room.monster.description)
        assertEquals( "friendship",room.monster.powerType)
        assertEquals(Int.MAX_VALUE,room.monster.healthPoints)
    }

    @Test
    fun getName() {
        assertEquals("A", room.name)
    }

    @Test
    fun getSize() {
        assertEquals(100, room.size)
    }
}