BUILD (needs JDK 21 + internet):
  1. Easiest: go to https://fabricmc.net/develop/template/ , pick Minecraft 1.21.11,
     download the template, then copy this project's src/ folder and the
     gradle.properties values over it (delete the template's example mixin/entrypoint).
     OR, if you have Gradle 8.12+ installed, run `gradle wrapper` in this folder.
  2. Run:   ./gradlew build        (Windows: gradlew.bat build)
  3. Your jar: build/libs/fastplace-1.0.0.jar  (NOT the -sources one)
  4. Put it in .minecraft/mods together with Fabric Loader + Fabric API for 1.21.11.

CONTROLS (change in Options > Controls):
  J = toggle on/off     [ = slower     ] = faster
  Delay 0 = max speed, 4 = vanilla. Hold right click to place.
