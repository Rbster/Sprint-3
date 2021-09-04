package ru.sber.io

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File
import java.util.*
import java.util.zip.ZipOutputStream

internal class ArchivatorTest {
    val archivator = Archivator()
    @Test
    fun zipUnzipLogfile() {
        archivator.zipLogfile()
        archivator.unzipLogfile()

        val bufLen = 512
        var bufferLog: ByteArray
        var bufferUnzippedLog: ByteArray

        archivator.logFile.inputStream().use {
                inputLog -> {
            archivator.unzippedLogFile.inputStream().use {
                    inputUnzippedLog -> {
                do {
                    bufferLog = inputLog.readNBytes(bufLen)
                    bufferUnzippedLog = inputUnzippedLog.readNBytes(bufLen)
                    assertTrue(bufferLog.contentEquals(bufferUnzippedLog))
                } while(bufferLog.isNotEmpty() && bufferUnzippedLog.isNotEmpty())
                assertTrue(bufferLog.isEmpty() && bufferUnzippedLog.isEmpty())
            } }
        } }



    }

//    @Test
//    fun copy() {
//        val inputFile = File("logfile.log")
//        File("copyOfLogfile.log").createNewFile()
//        val outputFile = File("copyOfLogfile.log")
//
//        val bufLen = 1024
//        var buffer: ByteArray
//        inputFile.inputStream().use { input ->
//            outputFile.outputStream().use { output ->
//                do {
//                    buffer = input.readNBytes(bufLen)
//                    output.write(buffer)
//                } while(buffer.isNotEmpty())
//            }
//        }
//    }
}