package ru.sber.oop

open class Room(val name: String, val size: Int) {

    protected open val dangerLevel = 5

    fun description() = "Room: $name"

    open fun load() = monster.getSalutation()

    // Inheritance 3.
    constructor(name: String) : this(name, 100)

    // Polymorphism 5.
    val monster: Monster = Goblin(
        name = "Bob",
        description = "Little green bro",
        powerType = "friendship",
        healthPoints = Int.MAX_VALUE)
}
// Polymorphism 6.
fun Monster.getSalutation() = "Hey! Come closer, mate"


//TODO: create class TownSquare here...
open class TownSquare() : Room(name = "Town Square", size = 1000) {
    final override fun load() = "No one would prefer to live here"
    override val dangerLevel = super.dangerLevel - 3
}

