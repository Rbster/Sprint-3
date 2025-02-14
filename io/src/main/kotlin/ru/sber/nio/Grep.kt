package ru.sber.nio

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Collectors
import kotlin.io.path.createFile
import kotlin.io.path.exists
import kotlin.io.path.isRegularFile
import kotlin.io.path.useLines

/**
 * Реализовать простой аналог утилиты grep с использованием калссов из пакета java.nio.
 */
class Grep {
    private val resultPath = Paths.get("result.txt")
    private val searchPath: Path = Paths.get("logs")
    private val result
        get() = if (resultPath.exists()) resultPath.toFile() else resultPath.createFile().toFile()



    /**
     * Метод должен выполнить поиск подстроки subString во всех файлах каталога logs.
     * Каталог logs размещен в данном проекте (io/logs) и внутри содержит другие каталоги.
     * Результатом работы метода должен быть файл в каталоге io(на том же уровне, что и каталог logs), с названием result.txt.
     * Формат содержимого файла result.txt следующий:
     * имя файла, в котором найдена подстрока : номер строки в файле : содержимое найденной строки
     * Результирующий файл должен содержать данные о найденной подстроке во всех файлах.
     * Пример для подстроки "22/Jan/2001:14:27:46":
     * 22-01-2001-1.log : 3 : 192.168.1.1 - - [22/Jan/2001:14:27:46 +0000] "POST /files HTTP/1.1" 200 - "-"
     */
    fun find(subString: String) = Files.walk(searchPath)
        .filter { path -> path.isRegularFile() }
        .map { path -> path.useLines { it
            .mapIndexed { index, s -> Pair(index, s) }
            .filter { p -> p.second.contains(subString) }
            .map { p -> "${path.fileName} : ${p.first + 1} : ${p.second}" }
            .joinToString(separator = "\n") } }
        .filter { it.isNotBlank() && !it.isNullOrEmpty() }
        .collect(Collectors.joining("\n"))
        .byteInputStream()
        .use { inputStream ->
            result.outputStream().use { fileOutputStream ->
                inputStream.transferTo(fileOutputStream)
            }
        }

}