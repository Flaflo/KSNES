@file:Suppress("MemberVisibilityCanBePrivate")

package xyz.flaflo.snes.rom.header

import xyz.flaflo.snes.rom.header.cartridge.SnesCartridge
import xyz.flaflo.snes.rom.header.cartridge.SnesEnhancementChip
import xyz.flaflo.snes.rom.header.layout.SnesBankType
import xyz.flaflo.snes.rom.header.layout.SnesRomLayout
import java.io.ByteArrayInputStream
import java.io.DataInputStream
import java.util.*

/**
 * Class that represents a value baked into the rom header
 *
 * @param parent the parent reader that his value underlies
 * @param offset the offset address inside the rom
 * @param size the size in bytes of the value
 * @param bytes the bytes this value is to be read from
 */
@ExperimentalUnsignedTypes
class SnesRomHeaderValue(private val parent: SnesRomHeaderReader, var offset: Int, private var size: Int, private var bytes: ByteArray) {
    private var prev: SnesRomHeaderValue? = null

    /**
     * Convenience constructor to chain all values together
     *
     * @param parent the parent reader that his value underlies
     * @param prev the previous header value
     * @param size the size in bytes of the value
     * @param bytes the bytes this value is to be read from
     */
    constructor(parent: SnesRomHeaderReader, prev: SnesRomHeaderValue, size: Int, bytes: ByteArray) : this(parent,0, size, bytes) {
        this.prev = prev

        reload()
    }

    /**
     * Recalculates the offset of this value and if this has a linked previous value
     * it will be invoked to reload as well
     */
    fun reload() {
        if (prev != null) {
            this.offset = prev!!.offset + prev!!.size
            prev!!.reload()
        }
    }

    /**
     * Reads and copies a byte array at the value's offset and in the size of this value
     * @return a byte array by the size of this value
     */
    fun readBytes(): ByteArray {
        return bytes.copyOfRange(offset, offset + size)
    }

    /**
     * Creates a DataInputStream from the value's bytes
     * @see DataInputStream
     * @return a DataInputStream
     */
    private fun reader(): DataInputStream {
        return DataInputStream(ByteArrayInputStream(readBytes()))
    }

    /**
     * Reads the first byte of the value's offset
     *
     * @return the first byte
     */
    fun readByte(): Byte {
        return bytes[offset]
    }

    /**
     * Reads a short number of this value
     *
     * @return a short number
     */
    fun readShort(): Short {
        return reader().readShort()
    }

    /**
     * Reads an unsigned short number of this value
     *
     * @return a unsigned short number
     */
    fun readUShort(): UShort {
        return readShort().toUShort()
    }

    /**
     * Reads the bytes as string from this value
     *
     * @return the bytes as string
     */
    fun readString(): String {
        return String(readBytes())
    }

    /**
     * Reads and converts this value into a SnesCartridgeType
     *
     * @see SnesCartridge
     * @return a SnesCartridgeType
     */
    fun readCartridgeType(): SnesCartridge {
        //Converting into a string and extracting the numbers out of it again is really ugly
        //But I could think of another way to do it at the moment and I wanted this to work
        //Improvements are welcome! :)

        val typeByte = parent.cartridgeType.readByte().toUInt()
        var type = typeByte.toString(16)

        if (typeByte < 10U) {
            type = "0$type"
        }

        val enhancementChip = SnesEnhancementChip.fromFlag(Integer.parseInt(type[0].toString()))

        val result = SnesCartridge(enhancementChip)

        when (Integer.parseInt(type[1].toString())) {
            0 -> result.enhancementChip = SnesEnhancementChip.None
            1 -> { result.ram = true; result.enhancementChip = SnesEnhancementChip.None }
            2 -> { result.ram = true; result.saveRam = true; result.enhancementChip = SnesEnhancementChip.None }
            4 -> result.ram = true
            5 -> { result.ram = true; result.saveRam = true }
            6 -> result.saveRam = true
            9 -> { result.ram = true; result.saveRam = true; result.rtc4513 = true }
            10 -> { result.ram = true; result.saveRam = true; result.ocGsu1 = true }
        }

        return result
    }

    /**
     * Reads and converts this value into a SnesRomLayout
     *
     * @see SnesRomLayout
     * @return a SnesRomLayout
     */
    fun readLayout(): SnesRomLayout {
        val bitSet = BitSet(parent.layout.readByte().toInt())

        return SnesRomLayout(
            if (bitSet[0]) SnesBankType.HiRom else SnesBankType.LoRom,
            bitSet[1],
            bitSet[2],
            bitSet[4] && bitSet[5]
        )
    }
}