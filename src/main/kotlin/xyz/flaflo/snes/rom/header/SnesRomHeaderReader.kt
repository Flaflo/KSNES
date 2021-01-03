package xyz.flaflo.snes.rom.header

import xyz.flaflo.snes.rom.header.layout.SnesBankType

/**
 * Class that is able to read rom header values from raw bytes
 *
 * @param bytes the raw rom bytes
 * @param offset the initial header offset (defaults to LoRom)
 */
@ExperimentalUnsignedTypes
class SnesRomHeaderReader(bytes: ByteArray, offset: Int = SnesBankType.LoRom.headerOffset) {
    val name = SnesRomHeaderValue(this, offset, 21, bytes)
    val layout = SnesRomHeaderValue(this, name, 1, bytes)
    val cartridgeType = SnesRomHeaderValue(this, layout, 1, bytes)
    val romSize = SnesRomHeaderValue(this, cartridgeType, 1, bytes)
    val saveRamSize = SnesRomHeaderValue(this, romSize, 1, bytes)
    val countryCode = SnesRomHeaderValue(this, saveRamSize, 1, bytes)
    val licensorCode = SnesRomHeaderValue(this, countryCode, 1, bytes)
    val version = SnesRomHeaderValue(this, licensorCode, 1, bytes)
    val checksumComplement = SnesRomHeaderValue(this, version, 2, bytes)
    val checksum = SnesRomHeaderValue(this, checksumComplement, 2, bytes)

    /**
     * Changes the header offset and forces the rom header values to reload with the new offset
     *
     * @param offset the new offset to be used
     */
    fun setOffset(offset: Int) {
        name.offset = offset
        checksum.reload()
    }
}