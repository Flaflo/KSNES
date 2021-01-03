package xyz.flaflo.snes.rom.header.cartridge

/**
 * Data class holding information about the cartridge hardware
 */
data class SnesCartridge(
    /**
     * The enhancement chip built inside the cartridge (if any)
     */
    var enhancementChip: SnesEnhancementChip = SnesEnhancementChip.None,
    /**
     * If true the cartridge contains a rom (always true)
     */
    val rom: Boolean = true,
    /**
     * If true the cartridge contains an additional ram chip
     */
    var ram: Boolean = false,
    /**
     * If true the cartridge contains a battery powered save ram chip to store game state across reboots
     */
    var saveRam: Boolean = false,
    /**
     * If true the cartridge contains an additional chip from EPSON (rtc4513)
     */
    var rtc4513: Boolean = false,
    /**
     * If true the cartridge contains the overclocked version of the gsu1 chip
     */
    var ocGsu1: Boolean = false
)