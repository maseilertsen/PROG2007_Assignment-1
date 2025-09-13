package functions

import mockLocation
import mockMineral

/**
 * Prints the main menu (user options) for the user.
 */
fun printMainMenu(){
    println("--- Main menu: ---\n" +
            "1 - Display geological location data and mineral data\n" +
            "2 - Location administration\n" +
            "3 - Mineral administration\n" +
            "4 - List all locations\n" +
            "5 - List all minerals\n" +
            "6 - Employee administration\n" +
            "0 - Shut down application\n" +
            "-------------------------\n")
}
/**
 * Lists all minerals and its data.
 */
fun listAllMinerals() {
    for (mineral in mockMineral) {
        // Check if max value is null
        var hardnessValue = "from ${mineral.hardness.min} to ${mineral.hardness.max}"
        if (mineral.hardness.max == null){ // Can be null when only one value is provided.
           hardnessValue = mineral.hardness.min.toString()
        }

        println("--- Mineral: ${mineral.name} ---\n" +
                "\tLuster:".padEnd(13)      + "${mineral.luster.displayName}\n"   +
                "\tColor:".padEnd(13)       + "${mineral.color.displayName}\n"    +
                "\tHardness:".padEnd(13)    + hardnessValue + "\n"                +
                "\tFracture:".padEnd(13)    + "${mineral.fracture.displayName}\n")
    }
}

/**
 * Lists all locations and their data.
 */
fun listAllLocation() {
    for (location in mockLocation) {
        print("--- Location: ${location.name} ---\n" +
        "\tDescription:".padEnd(15) + "${location.description}\n" +
        "\tCoordinates:".padEnd(15) +   "(${location.coordinates.latitude}," + "${location.coordinates.longitude})\n")

        if (location.notes != ""){ // Only print notes if they exist.
            print("\tNotes".padEnd(15) + "${location.notes}\n")
        }
        print('\n')
    }
}