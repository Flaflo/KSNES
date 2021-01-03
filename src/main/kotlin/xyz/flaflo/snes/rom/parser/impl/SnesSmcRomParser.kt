package xyz.flaflo.snes.rom.parser.impl

import xyz.flaflo.snes.rom.SnesRom
import xyz.flaflo.snes.rom.parser.api.ISnesRomParser
import java.io.InputStream

/**
 * Class for parsing a rom that contains a SMC header
 *
 * Basically it skips the header entirely right now because it has little to no use when we extract the real header
 * from the stripped rom
 *
 * @param inputStream the input stream to parse from
 */
@ExperimentalUnsignedTypes
class SnesSmcRomParser(override var inputStream: InputStream) : ISnesRomParser {
    companion object {
        /**
         * The length in bytes of the smc header
         */
        const val smc_header_length = 512L
    }

    override fun parse(): SnesRom {
        inputStream.skip(smc_header_length)

        return SnesRomParser(inputStream).parse()
    }
}