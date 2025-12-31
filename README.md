# Withered Lands

## Requirements
1. [NeoForge](https://neoforged.net/) (check "build.gradle" file to know required NeoForge version)
2. [Jdk 21.0.2](https://jdk.java.net/archive/) (do not forget to enable its support in your IDE)
3. [Gradle 9.2.1](https://gradle.org/releases/)

## Get started
1. Clone mod repository
2. Download NeoForge and copy "gradlew.bat", "gradlew" files and "gradle" directory to mod folder(and any other files which may requires)
3. Download MDK from NeoForge
4. Import mod to your ide as "new Gradle project"

## Gradle commands
1. Running client
    ```
        gradlew runClient
    ```
2. Running Server
    ```
        gradlew runServer
    ```
3. Build mod as .jar file
    ```
        gradlew build
    ```

For more information, look at "minecraft NeoForge" README.txt file (it's not included to this repository) or [this link](https://docs.neoforged.net/)

## Useful commands

1. Kill all
    ```
        /kill @e[type=!minecraft:player]
    ```

### Bats spawn:
1. Vampire bat
    ```
        /summon withered_lands:vampire_bat ~ ~ ~ {NoAI:1b}
    ```
2. Blazing bat
    ```
        /summon withered_lands:blazing_bat ~ ~ ~ {NoAI:1b}
    ```
3. Withered bat
    ```
        /summon withered_lands:withered_bat ~ ~ ~ {NoAI:1b}
    ```
4. Blazing bat
    ```
        /summon withered_lands:volatile_bat ~ ~ ~ {NoAI:1b}
    ```
5. Chorus bat
    ```
        /summon withered_lands:chorus_bat ~ ~ ~ {NoAI:1b}
    ```

### Wolves spawn:
1. Skeleton dog
    ```
        /summon withered_lands:skeleton_dog ~ ~ ~ {NoAI:1b}
    ```
2. Barghest
    ```
        /summon withered_lands:barghest ~ ~ ~ {NoAI:1b}
    ```

### Cats spawn:
1. Skeleton cat
    ```
        /summon withered_lands:skeleton_cat ~ ~ ~ {NoAI:1b}
    ``` 

### Water mobs spawn:
1. Phantom diver
    ```
        /summon withered_lands:phantom_diver ~ ~ ~ {NoAI:1b}
    ``` 
   