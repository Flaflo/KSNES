@file:Suppress("SpellCheckingInspection")

package xyz.flaflo.snes.tests

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import xyz.flaflo.snes.rom.header.capacity.SnesRomSize
import xyz.flaflo.snes.rom.header.capacity.SnesSaveRamSize
import xyz.flaflo.snes.rom.header.cartridge.SnesEnhancementChip
import xyz.flaflo.snes.rom.header.layout.SnesBankType
import xyz.flaflo.snes.rom.header.publishing.SnesRomCountry
import xyz.flaflo.snes.rom.header.publishing.SnesRomLicensor
import xyz.flaflo.snes.rom.parser.SnesRomParserProvider
import xyz.flaflo.snes.rom.parser.impl.SnesSmcRomParser
import java.io.File

@ExperimentalUnsignedTypes
class SnesParserTests {

    @Test
    fun testParserSuperMarioAllStars() {
        val parser = SnesRomParserProvider.fileParser(File("src/test/resources/smas.sfc"))
        val rom = parser.parse()

        assertEquals("SUPER MARIO ALL_STARS", rom.name)
        assertEquals(SnesBankType.LoRom, rom.layout.bankType)
        assertEquals(false, rom.layout.hasExHiRom)
        assertEquals(false, rom.layout.hasExLoRom)
        assertEquals(false, rom.layout.hasFastRom)
        assertEquals(SnesEnhancementChip.None, rom.cartridge.enhancementChip)
        assertEquals(true, rom.cartridge.ram)
        assertEquals(true, rom.cartridge.saveRam)
        assertEquals(false, rom.cartridge.rtc4513)
        assertEquals(false, rom.cartridge.ocGsu1)
        assertEquals(SnesRomSize.`16Mbit`, rom.romSize)
        assertEquals(SnesSaveRamSize.`64Kbit`, rom.saveRamSize)
        assertEquals(SnesRomCountry.Europe, rom.country)
        assertEquals(SnesRomLicensor.Nintendo, rom.licensor)
        assertEquals(0, rom.version)
        assertEquals(false, parser is SnesSmcRomParser)
    }

    @Test
    fun testParserSuperMarioWorld() {
        val parser = SnesRomParserProvider.fileParser(File("src/test/resources/smw.sfc"))
        val rom = parser.parse()

        assertEquals("SUPER MARIOWORLD", rom.name)
        assertEquals(SnesBankType.LoRom, rom.layout.bankType)
        assertEquals(false, rom.layout.hasExHiRom)
        assertEquals(false, rom.layout.hasExLoRom)
        assertEquals(false, rom.layout.hasFastRom)
        assertEquals(SnesEnhancementChip.None, rom.cartridge.enhancementChip)
        assertEquals(true, rom.cartridge.ram)
        assertEquals(true, rom.cartridge.saveRam)
        assertEquals(false, rom.cartridge.rtc4513)
        assertEquals(false, rom.cartridge.ocGsu1)
        assertEquals(SnesRomSize.`4Mbit`, rom.romSize)
        assertEquals(SnesSaveRamSize.`16Kbit`, rom.saveRamSize)
        assertEquals(SnesRomCountry.Europe, rom.country)
        assertEquals(SnesRomLicensor.Nintendo, rom.licensor)
        assertEquals(0, rom.version)
        assertEquals(false, parser is SnesSmcRomParser)
    }
}