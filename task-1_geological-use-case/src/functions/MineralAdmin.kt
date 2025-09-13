package functions

/**
 * Displays MineralAdmin-menu options.
 */
fun printMineralAdmin(){
    println("--- Location Administration ---\n" +
            "\t1 - Add location\n" +
            "\t2 - Update location\n" +
            "\t3 - Delete location\n" +
            "\t0 - Return to main menu\n"
    )
}

/**
 * Add, update and delete locations.
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
            println("\t!!! - Please enter an integer!\n" + "\tSystem: Returning to Location Administration menu\n")
            continue
        }
        when (opt) {
            1 -> addMineral()
            2 -> updateMineral()
            3 -> deleteMineral(null)
            0 -> println("System: Exiting Location administration...\n")
            !in 1..3 -> println("\t!!! - Not a valid option!\n") // "catch all" solution.
        }
    } while (opt != 0)
}

fun addMineral(){
//TODO
}

fun updateMineral(){
//TODO
}
fun deleteMineral(opt : Int?) {
//TODO
}
