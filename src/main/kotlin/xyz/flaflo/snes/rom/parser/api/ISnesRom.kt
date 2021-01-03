package xyz.flaflo.snes.rom.parser.api

import xyz.flaflo.snes.rom.header.capacity.SnesRomSize
import xyz.flaflo.snes.rom.header.capacity.SnesSaveRamSize
import xyz.flaflo.snes.rom.header.cartridge.SnesCartridge
import xyz.flaflo.snes.rom.header.layout.SnesRomLayout
import xyz.flaflo.snes.rom.header.publishing.SnesRomCountry
import xyz.flaflo.snes.rom.header.publishing.SnesRomLicensor

/**
 * Representation of a snes rom with additional information most likely extracted from the snes header
 * @see ISnesRomParser
 */
interface ISnesRom {
    /**
     * The internal name of the rom
     */
    val name: String

    /**
     * The rom layout information
     */
    val layout: SnesRomLayout

    /**
     * Information about the cartridge hardware
     */
    val cartridge: SnesCartridge

    /**
     * The rom size assumed by the rom header
     */
    val romSize: SnesRomSize

    /**
     * The size of the save ram used inside the cartridge
     */
    val saveRamSize: SnesSaveRamSize

    /**
     * The country the cartridge was released in
     */
    val country: SnesRomCountry

    /**
     * The licensor / publisher of the cartridge
     */
    val licensor: SnesRomLicensor

    /**
     * The rom version
     */
    val version: Int
}