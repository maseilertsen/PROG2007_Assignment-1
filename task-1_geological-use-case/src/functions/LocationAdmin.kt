package functions

import GeoPoint
import Location
import mockLocation

/**
 * Displays LocationAdmin-menu options.
 */
fun printLocationAdmin(){
    println("--- Location Administration ---\n" +
            "\t1 - Add location\n" +
            "\t2 - Update location\n" +
            "\t3 - Delete location\n" +
            "\t0 - Return to main menu\n"
    )
}

/**
 * Add, update and delete locations.
 * @see printLocationAdmin
 * @see addLocation
 * @see updateLocation
 * @see deleteLocation
 */
fun locationAdmin() {
    var opt : Int?
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
            2 -> updateLocation()
            3 -> deleteLocation(null)
            0 -> println("System: Exiting Location administration...\n")
            !in 1..3 -> println("\t!!! - Not a valid option!\n") // "catch all" solution.
        }

    } while (opt != 0)
}

/**
 * Adds a location from user input.
 */
fun addLocation(replace: Boolean = false) {
    println("\n--- Add Location Program ---")

    print(
        "\tEnter new location:\n" +
                "\t'<Name>,<Description>,<lat>,<long>'\n " +
                "\t->"
    )
    val newLocationString = readln()
    val newLocationParts = newLocationString.split(',')

    // Parting up the entered string.
    if (newLocationParts.size >= 4) {
        val name = newLocationParts[0].trim()
        val note = newLocationParts[1].trim()
        val lat = newLocationParts[2].trim().toDouble()
        val long = newLocationParts[3].trim().toDouble()

        val newLocation = Location(name, note, GeoPoint(lat, long))

        mockLocation.add(newLocation)

        if (replace)
            println("\tReplaced existing location named '$name'")
        else
            println("\tAdded: $newLocation")
    } else
        println("\tInvalid input, must be '<Name>,<Note>,<lat>,<long>'")

}

/**
 * Deletes a location provided by user input (String)
 * @see listAllLocation
 */
fun deleteLocation(replace: String?) {
    if (replace == null) {
        println("\n--- Remove Location Program ---")

        listAllLocation()
        print("----------------- Name of location to be deleted: ")
        val location = readln().trim()
        val found = mockLocation.firstOrNull { it.name == location }

        if (found != null) {
            print(
                "Are you sure you want to delete this location?" +
                        "\n---$found.name\n" +
                        "\t${found.description}\n" +
                        "\tChoices (y/n): "
            )
            val confirmation = readln().trim().uppercase()
            if (confirmation == "Y") {
                mockLocation.remove(found)
                println("\tRemoved: ${found.name}")
            } else {
                println("\tAborted deletion process")
                println("\tSystem: Returning to main menu\n")
                return
            }
        } else println("\tLocation not found check your spelling (Case sensitive!)\n")
    } else {
        val found = mockLocation.firstOrNull { it.name == replace }
        if (found != null) {
            mockLocation.remove(found)
        }
    }
}
/**
 * Updates a locations properties based on user input
 * @see listAllLocation
 */
fun updateLocation() {
    println("\n--- Update Location Program ---")
    listAllLocation()
    print("----------------- Name of location to be updated: ")
    val targetName = readln().trim()

    val index = mockLocation.indexOfFirst { it.name == targetName }
    if (index == -1) {
        println("\tLocation not found. Check your spelling (case sensitive!).\n")
        return
    }

    val found = mockLocation[index]
    println(
        "\nWhich field do you want to update?\n" +
                "1 - name: ${found.name}\n" +
                "2 - description: ${found.description}\n" +
                "3 - coordinates: ${found.coordinates.latitude}, ${found.coordinates.longitude}\n" +
                "4 - notes: ${found.notes}\n" +
                "5 - Replace all data\n" +
                "0 - cancel\n"
    )
    print("Choose a valid option: ")
    val opt = readln().toIntOrNull()
    if (opt == null) {
        println("!!! - Please enter an integer!")
        return
    }

    when (opt) {
        0 -> {
            println("Update cancelled.")
            return
        }
        1 -> {
            print("New name:  ")
            val input = readln()
            val updated = found.copy(name = input.trim())
            mockLocation[index] = updated
            println("-- Updated name to '${updated.name}'.")
        }
        2 -> {
            print("New description: ")
            val input = readln()
            val updated = found.copy(description = input.trim())
            mockLocation[index] = updated
            println("--Updated description.")
        }
        3 -> {
            println("Enter new coordinates as 'lat,long'")
            print("-> ")
            val line = readln().trim()
            val parts = line.split(',')
            if (parts.count {it == ","} >= 2){
                println("!!! - Invalid format. Don't use commas in decimal. use '.'")
                return
            }
            if (parts.size != 2) {
                println("!!! - Invalid format. Use: 59.91,10.75")
                return
            }
            val lat = parts[0].toDoubleOrNull()
            val long = parts[1].toDoubleOrNull()
            if (lat == null || long == null) {
                println("!!! - Latitude/Longitude must be numbers.")
                return
            }
            if (lat !in -90.0..90.0 || long !in -180.0..180.0) {
                println("!!! - Latitude must be in [-90, 90], Longitude in [-180, 180].")
                return
            }
            val updated = found.copy(coordinates = GeoPoint(lat, long))
            mockLocation[index] = updated
            println("Updated coordinates to ${lat}, ${long}.")
        }
        4 -> {
            print("New notes: ")
            val input = readln()
            val updated = found.copy(notes = input.trim())
            mockLocation[index] = updated
            println("Updated notes.")
        }
        5 ->{
            addLocation(found)
        }
        else -> println("!!! - Not a valid option!")
    }
}

/**
 * Replaces all attributes of named location.
 * Overloaded function.
 * @see deleteLocation
 * @see addLocation
 */
fun addLocation(found: Location){
    deleteLocation(found.name)
    addLocation(true)
}