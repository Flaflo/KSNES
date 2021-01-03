package xyz.flaflo.snes.rom.parser.api

import xyz.flaflo.snes.rom.SnesRom
import java.io.InputStream

/**
 * Class capable of parsing a snes rom via an input stream
 */
interface ISnesRomParser {
    /**
     * The input stream to parse from
     */
    val inputStream: InputStream

    /**
     * Executes the parsing on the provided input stream
     */
    fun parse(): SnesRom
}