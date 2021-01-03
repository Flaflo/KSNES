@file:Suppress("unused")

package xyz.flaflo.snes.rom.header.publishing

/**
 * Enumerates known country codes stored inside the rom header
 *
 * @param id the id that is used to store it in the snes header
 */
enum class SnesRomCountry(val id: Int) {
    Japan(0x00),
    America(0x01),
    Europe(0x02),
    Sweden(0x03),
    Finland(0x04),
    Denmark(0x05),
    France(0x06),
    Netherlands(0x07),
    Spain(0x08),
    Germany(0x09),
    Italy(0xA),
    China(0xB),
    Indonesia(0xC),
    SouthKorea(0xD),
    Common(0xE),
    Canada(0xF),
    Brazil(0x10),
    Australia(0x11);

    companion object {
        /**
         * @param id the country id
         * @return the country according to the provided flag
         */
        fun fromId(id: Int): SnesRomCountry {
            return values().first { it.id == id }
        }
    }
}