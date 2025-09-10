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