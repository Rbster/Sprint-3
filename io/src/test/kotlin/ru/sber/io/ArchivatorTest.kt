package ru.sber.io

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*


internal class ArchivatorTest {
    val archivator = Archivator()
    @Test
    fun zipUnzipLogfile() {
        archivator.zipLogfile()
        archivator.unzipLogfile()

        val bufLen = 512
        var bufferLog: ByteArray
        var bufferUnzippedLog: ByteArray

        archivator.logFile.inputStream().use { inputLog ->
            archivator.unzippedLogFile.inputStream().use { inputUnzippedLog ->
                do {
                    bufferLog = inputLog.readNBytes(bufLen)
                    bufferUnzippedLog = inputUnzippedLog.readNBytes(bufLen)
                    assertTrue(bufferLog.contentEquals(bufferUnzippedLog))
                } while (bufferLog.isNotEmpty() && bufferUnzippedLog.isNotEmpty())
                assertTrue(bufferLog.isEmpty() && bufferUnzippedLog.isEmpty())
            }
        }
    }
}