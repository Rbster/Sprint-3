package ru.sber.nio

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.nio.file.Paths
import kotlin.io.path.useLines

internal class GrepTest {

    @Test
    fun find() {
        val trueString = "22-01-2001-1.log : 3 : 192.168.1.1 - - [22/Jan/2001:14:27:46 +0000] \"POST /files HTTP/1.1\" 200 - \"-\""
        val queryString = "22/Jan/2001:14:27:46"
        Grep().find(queryString)

        val result = Paths.get("result.txt")
            .useLines { outerIt ->
                outerIt.joinToString(separator = "\n") }
        assertEquals(trueString,
            result)
    }
}