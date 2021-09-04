package ru.sber.nio

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.nio.file.Files
import java.util.stream.Collectors
import kotlin.io.path.isRegularFile
import kotlin.io.path.useLines

internal class GrepTest {

    @Test
    fun find() {
    }

    @Test
    fun testing() {
        val g = Grep()
        println(Files.walk(g.searchPath).filter { path -> path.isRegularFile() }
            .map { path -> path.fileName.toString() }.collect(Collectors.joining("//")))
    }
}