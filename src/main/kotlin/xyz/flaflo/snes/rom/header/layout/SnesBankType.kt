package xyz.flaflo.snes.rom.header.layout

/**
 * Enumerates existing bank layout types
 *
 * @param headerOffset the offset the header begins in this layout
 */
enum class SnesBankType(val headerOffset: Int) {
    LoRom(0x7FC0),
    HiRom(0xFFC0)
}