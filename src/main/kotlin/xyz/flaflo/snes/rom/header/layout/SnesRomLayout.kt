package xyz.flaflo.snes.rom.header.layout

/**
 * Data class holding information about the rom layout
 */
data class SnesRomLayout(
    /**
     * The bank layout type the rom uses
     */
    val bankType: SnesBankType,
    /**
     * If true the rom has an extended LoRom
     */
    val hasExLoRom: Boolean = false,
    /**
     * If true the rom has an extended HiRom
     */
    val hasExHiRom: Boolean = false,
    /**
     * If true the rom has a fast rom
     */
    val hasFastRom: Boolean = false
)