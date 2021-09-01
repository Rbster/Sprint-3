package ru.sber.oop

data class User(val name: String, val age: Long) {
    lateinit var city: String

    override fun equals(other: Any?) = (other is User)
            && this.name == other.name
            && this.age == other.age
            && this::city.isInitialized ==
                other::city.isInitialized
            && (! this::city.isInitialized
                || this::city.isInitialized
                && this.city == other.city)


}

fun main() {
    val user1 = User("Alex", 13)
    // user2
    val user2 = user1.copy(name = "Pavel")
    println("user2 is $user2")
    // user3
    user1.city = "Omsk"
    val user3 = user1.copy()
    user3.city = "Tomsk"
    println("user1 == user2 is ${user1 == user3}")
}