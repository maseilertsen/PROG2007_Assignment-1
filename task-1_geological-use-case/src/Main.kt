import functions.listAllLocation
import functions.listAllMinerals
import functions.locationAdmin
import functions.printMainMenu


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
            //1 -> displayGeologicalData()
            2 -> locationAdmin() // add, update and delete.
            //3 -> mineralAdmin() // add, update and delete.
            4 -> listAllLocation()
            5 -> listAllMinerals() // ..and it's data!
            0 -> println("System: Shutting down...")
            !in 1..5 -> println("\t!!! - Not a valid option!\n") // "catch all" solution.
        }
    } while (opt != 0)
}