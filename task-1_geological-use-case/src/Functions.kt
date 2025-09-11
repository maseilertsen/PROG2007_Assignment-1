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

/**
 * Add, update and delete locations.
 * @see printLocationAdmin
 * @see addLocation
 * @see updateLocation
 * @see deleteLocation
 */
fun locationAdmin() {
    var opt : Int? = null
    do {
        printLocationAdmin()
        print("Choose a valid option: ")
         opt = readln().toIntOrNull()
        if (opt == null) {
            println("\t!!! - Please enter an integer!\n" + "\tSystem: Returning to Location Administration menu\n")
            continue
        }
            when (opt) {
                // TODO: Implement functions
                1 -> addLocation()
                //2 -> updateLocation()
                //3 -> deleteLocation()
                0 -> println("System: Exiting Location administration...\n")
                !in 1..3 -> println("\t!!! - Not a valid option!\n") // "catch all" solution.
            }

    } while (opt != 0)
}

fun printLocationAdmin(){
    println("--- Location Administration ---\n" +
            "\t1 - Add location\n" +
            "\t2 - Update location\n" +
            "\t3 - Delete location\n" +
            "\t0 - Return to main menu\n"
    )
}

fun addLocation(){
    println("\n--- Add Location Program---")

    print("\tEnter new location:\n"+
    "\t'<Name>,<Description>,<lat>,<long>'\n " +
    "\t->")
    val newLocationString = readln()
    val newLocationParts = newLocationString.split(',')

    // Parting up the entered string.
    if (newLocationParts.size >= 4) {
        val name = newLocationParts[0].trim()
        val note = newLocationParts[1].trim()
        val lat = newLocationParts[2].trim().toDouble()
        val long = newLocationParts[3].trim().toDouble()

        val newLocation = Location(name,note,GeoPoint(lat, long)
    )
        mockLocation.add(newLocation)
        println("\tAdded: $newLocation")
    } else {
        println("\tInvalid input, must be '<Name>,<Note>,<lat>,<long>'")
    }
    return
}