package functions

import Employee
import mockEmployee

/**
 * Displays MineralAdmin-menu options.
 */
fun printEmployeeAdmin(){
    println("--Employee Administration ---\n" +
            //"\t1 - Add mineral\n" +
            //"\t2 - Update mineral\n" +
            //"\t3 - Delete mineral\n" +
            "\t4 - Sort employees\n" +
            "\t5 - list all employees\n" +
            "\t6 - list one employee\n" +
            "\t0 - Return to main menu\n"
    )
}
/**
 * Add, update and delete minerals.
 */
fun employeeAdmin() {
    var opt : Int?
    do {
        printEmployeeAdmin()
        print("Choose a valid option: ")
        opt = readln().toIntOrNull()
        if (opt == null) {
            println("\t!!! - Please enter an integer!\n" + "\tSystem: Returning to Employee Administration menu\n")
            continue
        }
        when (opt) {
            4 -> sortMineral()
            5 -> listAllEmployees()
            6 -> printOneEmployee()
            0 -> println("System: Exiting Employee administration...\n")
            !in 1..3 -> println("\t!!! - Not a valid option!\n") // "catch all" solution.
        }
    } while (opt != 0)
}

/**
 * Lists a (1) employee and their data.
 */
fun printEmployee(employee: Employee) {
    println("ID-" + employee.employeeId + " -- name: " + employee.name.firstName+ " " + employee.name.lastName)
    println("\tPhone number: " + employee.phoneNumber)
    println("\tHourly wage" + employee.hourlyWage)
    println("\tCurrent work: ")

    for (work in employee.work) { // all work information
        println("\t\tDay: ${work.workingDays}")
        println("\t\tLocation: ${work.location.name} - ${work.location.description}")
        println("\t\tHours: ${work.startTime} - ${work.endTime}")
    }
    println()
}

/**
 * Lists all employees in database (list)
 */
fun listAllEmployees() {
    for (employee in mockEmployee){
        printEmployee(employee)
    }
}

/**
 * Search for employee in database (list)
 * @see Employee
 * @return Instance of Employee or null
 */
fun findEmployee(): Employee? {
    println("\nSearch for an employee (first name): ")
    print("->")
    val name = readln().trim()

    val found = mockEmployee.firstOrNull{it.name.firstName == name}
    if (found != null) {
        println("Found Employee: ${found.name.firstName} ${found.name.lastName}")
        return found
     } else {
         println("Employee not found")
        return null
     }
}

/**
 * Prints one specific employee and their info
 * @see findEmployee
 * @see printEmployee
 */
fun printOneEmployee() {
    val employee = findEmployee()
    if (employee != null) {
        printEmployee(employee)
    } else{
        println("Could not print ghost..")
    }
}