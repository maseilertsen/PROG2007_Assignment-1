import functions.employeeAdmin
import functions.findsMenu
import functions.listAllLocation
import functions.listAllMinerals
import functions.locationAdmin
import functions.printMainMenu
import functions.mineralAdmin


/**
 * Reads user input, and calls functions to perform program function.
 * @see functions.printMainMenu
 */
fun main() {
    do {
        printMainMenu()
       print("Choose a valid option: ")
        val opt = readln().toIntOrNull() ?: println("\t!!! - Please enter an integer!")
        when (opt) {
            // TODO: Implement functions
            1 -> findsMenu()
            2 -> locationAdmin() // add, update and delete.
            3 -> mineralAdmin() // add, update and delete.
            4 -> employeeAdmin()
            0 -> println("System: Shutting down...")
            !in 1..4 -> println("\t!!! - Not a valid option!\n") // "catch all" solution.
        }
    } while (opt != 0)
}