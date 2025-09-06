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
        val opt = readln().toIntOrNull() ?: invalidInt(1,5, 0)
        when (opt) {
            // TODO: Implement functions
            //1 -> displayGeologicalData()
            //2 -> locationAdmin() // add, update and delete.
            //3 -> mineralAdmin() // add, update and delete.
            //4 -> listAllLocation()
            //5 -> listAllMinerals() // ..and it's data!
            0 -> println("Shutting down...")
        }
    } while (opt != 0)

}

/**
 *  Prompts user with valid Integer input, and exit value
 * @param start - start of displayed range
 * @param end - end of displayed range
 * @param other optional: defaults to 999 if not provided
 */
fun invalidInt(start: Int, end: Int, other: Int = 999){
    if (other == 999){
        println("\t!!! Please enter a valid option ($start-$end)")
    } else {
    println("\t!!! Please enter a valid option ($start-$end) or $other to exit the program)\n")
    }
}