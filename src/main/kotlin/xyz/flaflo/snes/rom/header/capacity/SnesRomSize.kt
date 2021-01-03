@file:Suppress("EnumEntryName", "EnumEntryName", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused"
)

package xyz.flaflo.snes.rom.header.capacity

/**
 * Enumerates the possible rom sizes that can be determined by the rom header
 *
 * @param flag the flag that is used to store the rom size into the header
 */
enum class SnesRomSize(val flag: Int) {
    Unknown(0x00),
    `2Mbit`(0x08),
    `4Mbit`(0x09),
    `8Mbit`(0x0A),
    `16Mbit`(0x0B),
    `32Mbit`(0x0C),
    `48Mbit`(0x0D);

    companion object {
        /**
         * @param flag the rom size flag
         * @return the rom size according the the provided flag
         */
        fun fromFlag(flag: Int): SnesRomSize {
            return values().firstOrNull { it.flag == flag } ?: Unknown
        }
    }
}