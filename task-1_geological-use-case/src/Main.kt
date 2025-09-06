/**
 * Prints the main menu (user options) for the user.
 */
fun printMainMenu(){
   println("--- Main menu: ---\n" +
           "1 - Display geological location data and mineral data\n" +
           "2 - Location administration\n" +
           "3 - Mineral administration" +
           "4 - List all locations\n" +
           "5 - List all minerals\n" +
           "0 - Shut down application\n" +
           "-------------------------\n")
}
/**
 * Reads user input, and calls functions to perform program function.
 * @see printMainMenu
 */
fun main() {
    do {
        printMainMenu()
       print("Choose a valid option: ")
        val opt = readln().toIntOrNull() ?: println("\t!!! - Please enter an integer!")
        when (opt) {
            // TODO: Implement functions
            //1 -> displayGeologicalData()
            //2 -> locationAdmin() // add, update and delete.
            //3 -> mineralAdmin() // add, update and delete.
            //4 -> listAllLocation()
            //5 -> listAllMinerals() // ..and it's data!
            0 -> println("Shutting down...")
            !in 1..5 -> println("\t!!! - Not a valid option!\n") // "catch all" solution.
        }
    } while (opt != 0)

}