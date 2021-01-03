# KSNES
Tinkering with SNES and emulation with Kotlin

## Reading a SNES ROM
Currently KSNES is capable of reading and parsing information from SNES ROM files.
This can be achieved as follows

```kotlin
val parser = SnesRomParserProvider.fileParser(File("example.sfc"))
val rom = parser.parse()

println(rom.name)
```

# Contributing
Pull-Requests are highly appreciated!

## Building
So far this project has no dependencies at all so this is an easy one

1. ``git clone https://github.com/Flaflo/KSNES.git``
2. ``cd KSNES``
3. ``gradle build`` *

*One thing to mention is that the unit tests will most likely fail while trying to build because I am not pushing the ROM files obviously.
So unless I get my hands on test ROMs that I am allowed to upload you need to download them yourself and put them into ``src/test/resources/``

The following files are required:
- ``smw.sfc`` - Super Mario World (Europe, No SMC)
- ``smas.sfc`` - Super Mario All-Stars (Europe, No SMC)

# Credits
Providing a full fledged documentation about the SNES and its functionality:
- https://problemkaputt.de/fullsnes.htm
