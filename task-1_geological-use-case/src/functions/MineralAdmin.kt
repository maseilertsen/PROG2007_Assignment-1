package functions
import Hardness
import Mineral
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
fun addMineral(){
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

    println("Added mineral: $mineral")
    }


fun updateMineral(){
//TODO
}

/**
 * Deletes a mineral named by user input.
 * @see listAllMinerals
 */
fun deleteMineral(opt : Int?) {
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
    if (confirmation == "y"){
        mockMineral.remove(found)
        println("Deleted mineral: ${found.name}")
    }
}
