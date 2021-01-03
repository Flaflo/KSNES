package xyz.flaflo.snes.rom.parser

import xyz.flaflo.snes.rom.parser.api.ISnesRomParser
import xyz.flaflo.snes.rom.parser.impl.SnesSmcRomParser
import xyz.flaflo.snes.rom.parser.impl.SnesRomParser
import java.io.File

/**
 * Singleton provider for easy snes rom parser access
 */
@ExperimentalUnsignedTypes
object SnesRomParserProvider {

    /**
     * @return the correct parser for the file format (smc parser for smc header; headless parser for stripped rom)
     */
    fun fileParser(file: File): ISnesRomParser =
        if (file.length() % 1024 == 512L)
            SnesSmcRomParser(file.inputStream())
        else
            SnesRomParser(file.inputStream())
}