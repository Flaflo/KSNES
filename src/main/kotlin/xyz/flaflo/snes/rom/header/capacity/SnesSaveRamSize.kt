@file:Suppress("EnumEntryName", "EnumEntryName", "EnumEntryName", "EnumEntryName", "EnumEntryName", "unused", "unused",
    "unused", "unused"
)

package xyz.flaflo.snes.rom.header.capacity

/**
 * Enumerates the possible save ram sizes that can be determined by the rom header
 *
 * @param flag the flag that is used to store the save ram size into the header
 */
enum class SnesSaveRamSize(val flag: Int) {
    Unknown(-1),
    None(0x00),
    `16Kbit`(0x01),
    `32Kbit`(0x02),
    `64Kbit`(0x03),
    `128Kbit`(0x04),
    `256Kbit`(0x05);

    companion object {
        /**
         * @param flag the save ram size flag
         * @return the save ram size according to the provided flag
         */
        fun fromFlag(flag: Int): SnesSaveRamSize {
            return values().firstOrNull { it.flag == flag } ?: Unknown
        }
    }
}