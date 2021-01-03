@file:Suppress("unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused")

package xyz.flaflo.snes.rom.header.cartridge

/**
 * Enumerates known enhancement chips that were inbuilt into snes cartridges
 *
 * @param flag the flag that is used to store it in the snes header
 */
enum class SnesEnhancementChip(val flag: Int) {
    /**
     * DSP1, DSP1A, DSP1B, DSP2, DSP3, DSP4
     */
    Dsp(0x00),

    /**
     * MarioChip1, GSU1, GSU2, GSU2-SP1
     */
    Gsu(0x01),
    Obc1(0x02),
    Sa1(0x03),
    Sdd1(0x04),
    SRtc(0x05),

    /**
     * Super Gameboy / Satellaview
     */
    Other(0x0E),
    Custom(0x0F),
    None(-1);

    companion object {
        /**
         * @param flag the enhancement chip flag
         * @return the enhancement chip according to the provided flag
         */
        fun fromFlag(flag: Int): SnesEnhancementChip {
            return values().firstOrNull { it.flag == flag } ?: None
        }
    }
}