package functions
import GeoPoint
import Hardness
import Mineral
import mockLocation
import mockMineral


/**
 * Displays MineralAdmin-menu options.
 */
fun printMineralAdmin(){
    println("--Mineral Administration ---\n" +
            "\t1 - Add mineral\n" +
            "\t2 - Update mineral\n" +
            "\t3 - Delete mineral\n" +
            "\t0 - Return to main menu\n"
    )
}

/**
 * Add, update and delete minerals.
 * @see printMineralAdmin
 * @see addMineral
 * @see updateMineral
 * @see deleteMineral
 */
fun mineralAdmin() {
    var opt : Int?
    do {
        printMineralAdmin()
        print("Choose a valid option: ")
        opt = readln().toIntOrNull()
        if (opt == null) {
            println("\t!!! - Please enter an integer!\n" + "\tSystem: Returning to Mineral Administration menu\n")
            continue
        }
        when (opt) {
            1 -> addMineral()
            2 -> updateMineral()
            3 -> deleteMineral(null)
            0 -> println("System: Exiting Mineral administration...\n")
            !in 1..3 -> println("\t!!! - Not a valid option!\n") // "catch all" solution.
        }
    } while (opt != 0)
}

/**
 * Adds a mineral using user input
 */
fun addMineral(replace: Boolean = false){
    println("\n--- Add Mineral Program ---")

    print("\tEnter new mineral:\n"+
            "\t'<Name>,<Luster>,<Color>,<minHardness-maxHardness>,<Fracture>'\n " +
            "\t->")

    val newMineralString = readln()
    val newMineralParts = newMineralString.split(',')           // User input.
    val hardnessParts = newMineralParts[3].trim().split('-')    // Hardness.

    //  This not implement input sanitization or proper error handling for malicious or incorrect data.
    val mineral = Mineral(
        name = newMineralParts[0].trim(),
        luster = Luster.valueOf(newMineralParts[1].trim().uppercase()),
        color = Color.valueOf(newMineralParts[2].trim().uppercase()),
        hardness = Hardness(
            min = hardnessParts[0].toDouble(),
            max = hardnessParts[1].toDouble()
        ),
        fracture = Fracture.valueOf(newMineralParts[4].trim().uppercase())
    )

    mockMineral.add(mineral)

    if (replace) println("\tReplaced exising mineral named '${mineral.name}'\n\n")
        else println("\tAdded mineral: $mineral")

    }

/**
 * Updates ONE property of a mineral, or replaces everything with input from user.
 * @see listAllMinerals
 * @see replaceMineral
 */
fun updateMineral(){
    println("\n--- Update mineral Program ---")
    listAllMinerals()
    print("----------------- Name of mineral to be updated: ")
    val targetName = readln().trim()

    val index = mockMineral.indexOfFirst { it.name == targetName }
    if (index == -1){ // name not found in data
        println("\tMineral not found. Check your spelling (case sensitive!).\n")
        return
    }

    val found = mockMineral[index]
    println(
        "\nWhich field do you want to update?\n" +
                "1 - name: ${found.name}\n" +
                "2 - luster: ${found.luster}\n" +
                "3 - color: ${found.color}")
    if (found.hardness.max != null)
        print("4 - hardness: ${found.hardness.min}, ${found.hardness.max}\n")
   else print("4 - hardness: ${found.hardness.min}\n")
          print("5 - fracture: ${found.fracture}\n" +
                "6 - Replace all data\n" +
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
        1 -> { // update name
            print("Enter new name:  ")
            val input = readln()
            val updated = found.copy(name = input.trim())
            mockMineral[index] = updated
            println("-- Updated name to: ${updated.name}")

        }
        2 ->{ // update luster
            println("valid options:")
            print('\t'); Luster.entries.forEach { print("${it.displayName} ") }
            print("Enter new luster property: ")

            val input = readln()
            val luster = Luster.valueOf(input.trim().uppercase())
            val updated = found.copy(luster = luster)
            mockMineral[index] = updated
            println("-- Updated luster to: ${updated.luster.displayName}")

        }
        3 -> { // update color
            println("valid options:")
            print('\t'); Color.entries.forEach { print("${it.displayName} ") }
            print("Enter new color property: ")

            print("Enter new color: ")
            val input = readln()
            val color = Color.valueOf(input.trim().uppercase())
            val updated = found.copy(color = color)
            mockMineral[index] = updated
            println("-- Updated color to: ${updated.color}")
        }
        4 ->{ // hardness
            println("Enter new hardness as 'min-max(optional)'")
            print("-> ")
            val line = readln().trim()
            val parts = line.split('-')

            if (parts.size == 1){ // only one value passed.
                println("ONLY ONE VALUE") // TODO remove debug print
                val min = parts[0].toDoubleOrNull()
                if (min != null){
                    val updated = found.copy(hardness = Hardness(min,null))
                    mockMineral[index] = updated
                    println("-- Updated hardness to ${updated.hardness.min}")
                    return // return to menu after update.
                }
            }

            var min = parts[0].toDoubleOrNull()
            val max = parts[1].toDoubleOrNull()
            if (parts.size == 2) {
                if (min == null && max == null) {
                    println("!!! - Hardness-values must be numbers.")
                    return
                } else {
                    min = parts[0].toDouble() // Need to be Double, not Double? after validation.
                }


            val updated = found.copy(hardness = Hardness(min, max))
            mockMineral[index] = updated
            println("Updated hardness to ${min}, ${max}.")
            } else {
                println("!!! - Invalid format. Use: '01-23' <- This is an example")
                return
            }
        }
        5 -> { // fracture
            println("valid options:")
            print('\t'); Fracture.entries.forEach { print("${it.displayName} ") }
            print("Enter new fracture property: ")

            val input = readln()
            val fracture = Fracture.valueOf(input.trim().uppercase())
            val updated = found.copy(fracture = fracture)
            mockMineral[index] = updated
            println("-- Updated fracture to: ${updated.luster.displayName}")
        }
        6 -> { // replace all
            replaceMineral(found)

        }
        else -> println("!!! - Not a valid option!")
    }
}

/**
 * Deletes a mineral named by user input.
 * @see listAllMinerals
 */
fun deleteMineral(replace: String?) {
    if (replace == null) {
        listAllMinerals()
        print("----------------- Name of mineral to be deleted: ")
        val input = readln().trim()
        val found = mockMineral.firstOrNull() { it.name == input }

        if (found != null) {
            print("Are you sure you want to delete ${found.name}? (y/n): ")
        } else {
            println("\tno mineral with that name...check spelling (case sensitive)")
            println("\tSystem: Returning to main menu\n")
            return
        }
        val confirmation = readln().trim().uppercase()
        if (confirmation == "y") {
            mockMineral.remove(found)
            println("Deleted mineral: ${found.name}")
        }
    } else {
        val found = mockMineral.firstOrNull() { it.name == replace }
        if (found != null) {
            mockMineral.remove(found)
        }
    }
}

fun replaceMineral(found: Mineral){
    deleteMineral(found.name)
    addMineral(true)
}