package xyz.flaflo.snes.rom.header.publishing

/**
 * Enumerates known licensors and publishers saved inside the rom header
 *
 * Many of the are missing because I did not have any time to research them
 *
 * Any contribution to this is welcome :)
 *
 * @param id the id that is used to store it in the snes header
 */
enum class SnesRomLicensor(val id: Int) {
    Unknown(0x00),
    Nintendo(0x01);

    companion object {
        /**
         * @param id the licensor flag
         * @return the licensor according to the provided flag
         */
        fun fromId(id: Int): SnesRomLicensor {
            return values().firstOrNull { it.id == id } ?: Unknown
        }
    }
}