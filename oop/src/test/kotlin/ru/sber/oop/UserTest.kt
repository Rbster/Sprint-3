package ru.sber.oop

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class UserTest {

    @Test
    fun testEquals() {
        val user1 = User("Alex", 12)
        val user2 = user1.copy()
        assertTrue(user1 == user2)
    }

    @Test
    fun testEquals_firstCityIsNull() {
        val user1 = User("Alex", 12)
        val user2 = user1.copy()
        user2.city = "Moscow"
        assertFalse(user1 == user2)
    }

    @Test
    fun testEquals_secondCityIsNull() {
        val user1 = User("Alex", 12)
        user1.city = "Moscow"
        val user2 = user1.copy()
        assertFalse(user1 == user2)
    }

    @Test
    fun testEquals_citiesAreNotNull_equal() {
        val user1 = User("Alex", 12)
        user1.city = "Moscow"
        val user2 = user1.copy()
        user2.city = "Moscow"
        assertTrue(user1 == user2)
    }

    @Test
    fun testEquals_citiesAreNotNull_notEqual() {
        val user1 = User("Alex", 12)
        user1.city = "Moscow"
        val user2 = user1.copy()
        user2.city = "Spb"
        assertFalse(user1 == user2)
    }
}