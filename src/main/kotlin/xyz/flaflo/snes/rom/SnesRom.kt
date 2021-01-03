package xyz.flaflo.snes.rom

import xyz.flaflo.snes.rom.header.cartridge.SnesCartridge
import xyz.flaflo.snes.rom.header.publishing.SnesRomCountry
import xyz.flaflo.snes.rom.header.publishing.SnesRomLicensor
import xyz.flaflo.snes.rom.header.layout.SnesRomLayout
import xyz.flaflo.snes.rom.header.capacity.SnesRomSize
import xyz.flaflo.snes.rom.header.capacity.SnesSaveRamSize
import xyz.flaflo.snes.rom.parser.api.ISnesRom

/**
 * Implementation of a snes rom
 *
 * @see ISnesRom
 */
data class SnesRom(
    override val name: String,
    override val layout: SnesRomLayout,
    override val cartridge: SnesCartridge,
    override val romSize: SnesRomSize,
    override val saveRamSize: SnesSaveRamSize,
    override val country: SnesRomCountry,
    override val licensor: SnesRomLicensor,
    override val version: Int
) : ISnesRom