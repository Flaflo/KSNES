package xyz.flaflo.snes.rom.parser.impl

import xyz.flaflo.snes.rom.SnesRom
import xyz.flaflo.snes.rom.header.SnesRomHeaderReader
import xyz.flaflo.snes.rom.header.capacity.SnesRomSize
import xyz.flaflo.snes.rom.header.capacity.SnesSaveRamSize
import xyz.flaflo.snes.rom.header.layout.SnesBankType
import xyz.flaflo.snes.rom.header.publishing.SnesRomCountry
import xyz.flaflo.snes.rom.header.publishing.SnesRomLicensor
import xyz.flaflo.snes.rom.parser.api.ISnesRomParser
import java.io.InputStream

/**
 * Class for parsing a stripped snes rom
 *
 * @param inputStream the input stream to parse from
 */
@ExperimentalUnsignedTypes
class SnesRomParser(override var inputStream: InputStream) : ISnesRomParser {

    override fun parse(): SnesRom {
        return buildRomWithHeader(prepareHeader(inputStream.readAllBytes()))
    }

    /**
     * Creates the rom object
     *
     * @param header a prepared rom header reader
     * @return the snes rom
     */
    private fun buildRomWithHeader(header: SnesRomHeaderReader): SnesRom {
        val name = header.name.readString()
        val layout = header.layout.readLayout()
        val romSize = SnesRomSize.fromFlag(header.romSize.readByte().toInt())
        val saveRamSize = SnesSaveRamSize.fromFlag(header.saveRamSize.readByte().toInt())
        val country = SnesRomCountry.fromId(header.countryCode.readByte().toInt())
        val licensor = SnesRomLicensor.fromId(header.licensorCode.readByte().toInt())
        val version = header.version.readByte().toInt()
        val cartridgeType = header.cartridgeType.readCartridgeType()

        return SnesRom(
            name.trim(),
            layout,
            cartridgeType,
            romSize,
            saveRamSize,
            country,
            licensor,
            version
        )
    }

    /**
     * Prepares a rom header reader by feeding it with raw bytes and guessing the bank type
     * @see guessBankType
     *
     * @param bytes the raw rom bytes
     * @return a fully prepared rom header reader to which can be read from
     */
    private fun prepareHeader(bytes: ByteArray): SnesRomHeaderReader {
        val header = SnesRomHeaderReader(bytes)
        val bankType = guessBankType(header)

        if (bankType != SnesBankType.LoRom) {
            header.setOffset(bankType.headerOffset)
        }

        return header
    }

    /**
     * Initially guesses the rom bank layout by trying to read and verify the checksum with the LoRom layout
     *
     * If the checksum is not correct HiRom is assumed
     *
     * @return the guessed bank type
     */
    private fun guessBankType(header: SnesRomHeaderReader): SnesBankType {
        val complement = header.checksumComplement.readUShort()
        val checksum = header.checksum.readUShort()

        return if ((checksum xor complement) == UShort.MAX_VALUE) {
            SnesBankType.LoRom
        } else {
            SnesBankType.HiRom
        }
    }
}