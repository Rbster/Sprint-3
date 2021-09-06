package ru.sber.oop

import kotlin.random.Random

interface Fightable {
    val powerType: String
    var healthPoints: Int
    val damageRoll: Int
        get() {
            return Random.nextInt()
        }

    fun attack(opponent: Fightable): Int
}

//TODO: create class Player, Monster, Goblin here...
class Player(val name: String,
             val isBlessed: Boolean,
             override val powerType: String,
             override var healthPoints: Int
             ) : Fightable {
    override fun attack(opponent: Fightable): Int {
        val damage = if (isBlessed) 2 * damageRoll else damageRoll
        opponent.healthPoints -= damage
        return damage
    }
}
abstract class Monster : Fightable {
    abstract val name: String
    abstract val description: String
    override fun attack(opponent: Fightable): Int {
        val damage = damageRoll
        opponent.healthPoints -= damage
        return damage
    }
}

class Goblin(
    override val name: String,
    override val description: String,
    override val powerType: String,
    override var healthPoints: Int
) : Monster() {
    override val damageRoll: Int
        get() = super.damageRoll / 2
}
