package ru.sber.nio

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File
import java.nio.file.Files
import java.util.stream.Collectors
import kotlin.io.path.isRegularFile
import kotlin.io.path.useLines

internal class GrepTest {

    @Test
    fun find() {
        val trueString = "22-01-2001-1.log : 3 : 192.168.1.1 - - [22/Jan/2001:14:27:46 +0000] \"POST /files HTTP/1.1\" 200 - \"-\""
        var buffer: ByteArray
        val bufLen = 512
        val gotString = StringBuffer()
        val queryString = "22/Jan/2001:14:27:46"
        Grep().find(queryString)
        File("result.txt").inputStream().use {
            do {
                buffer = it.readNBytes(bufLen)
                gotString.append(buffer.toString())
            } while (buffer.isNotEmpty())
            println(trueString)
            println(gotString.toString())  // something with encoding...
            assertEquals(trueString, gotString.toString())  // it's okay in result.txt
        }
    }
}