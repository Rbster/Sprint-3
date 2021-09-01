package ru.sber.oop

open class Room(val name: String, val size: Int) {

    protected open val dangerLevel = 5

    fun description() = "Room: $name"

    open fun load() = "Nothing much to see here..."

    // 3.
    constructor(name: String) : this(name, 100)

}

//TODO: create class TownSquare here...
open class TownSquare() : Room(name = "Town Square", size = 1000) {
    final override fun load() = "It's dark. So there isn't much to see either..."
    override val dangerLevel = super.dangerLevel - 3
}

