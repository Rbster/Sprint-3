package ru.sber.io

import java.io.File
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

/**
 * Реализовать методы архивации и разархивации файла.
 * Для реализиации использовать ZipInputStream и ZipOutputStream.
 */
class Archivator {
    private val logFileName = "logfile.log"
    private val zippedLogFileName = "logfile.zip"
    private val unzippedLogFileName = "unzippedLogfile.log"

    val logFile = File(logFileName)
    private val zippedLogFile: File
        get() {
            File(zippedLogFileName).createNewFile()
            return File(zippedLogFileName)
        }
    val unzippedLogFile: File
        get() {
            File(unzippedLogFileName).createNewFile()
            return File(unzippedLogFileName)
        }
    /**
     * Метод, который архивирует файл logfile.log в архив logfile.zip.
     * Архив должен располагаться в том же каталоге, что и исходной файл.
     */
    fun zipLogfile() {
        val bufLen = 1024
        var buffer: ByteArray
        logFile.inputStream().use { input ->
            ZipOutputStream(zippedLogFile.outputStream()).use { output ->
                output.putNextEntry(ZipEntry(logFileName))
                do {
                buffer = input.readNBytes(bufLen)
                output.write(buffer)
                } while(buffer.isNotEmpty())
            }
        }
    }

    /**
     * Метод, который извлекает файл из архива.
     * Извлечь из архива logfile.zip файл и сохарнить его в том же каталоге с именем unzippedLogfile.log
     */
    fun unzipLogfile() {
        val bufLen = 1024
        var buffer: ByteArray
        ZipInputStream(zippedLogFile.inputStream()).use { input ->
            input.nextEntry
            unzippedLogFile.outputStream().use { output ->
                do {
                    buffer = input.readNBytes(bufLen)
                    output.write(buffer)
                } while(buffer.isNotEmpty())
            }
        }
    }
}