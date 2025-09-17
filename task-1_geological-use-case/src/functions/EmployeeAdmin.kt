package functions

import Employee
import functions.findEmployee
import mockEmployee
import kotlin.time.Duration
import java.time.DayOfWeek

/**
 * Displays MineralAdmin-menu options.
 */
fun printEmployeeAdmin(){
    println("--Employee Administration ---\n" +
            //"\t1 - Add mineral\n" +     // TODO
            //"\t2 - Update mineral\n" +  // TODO part of issue 15. Will need to come back to this if time.
            //"\t3 - Delete mineral\n" +  // TODO
            "\t4  - Sort employees\n" +
            "\t5  - list all employees\n" +
            "\t6  - list one employee\n" +
            "\t7  - Employee monthly paycheck\n" +
            "\t8  - All monthly paychecks\n" +
            "\t9  - sort employees\n" +
            "\t10 - list all nomads\n" +
            "\t0  - Return to main menu\n"
    )
}
/**
 * Main loop of employee menu
 * @see printEmployeeAdmin
 * @see sortMineral
 * @see listAllEmployees
 * @see printOneEmployee
 * @see monthlyPaycheck
 * @see allMonthlyPaycheck
 * @see sortEmployees
 * @see nomadEmployees
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
            6 -> printOneEmployee(null)
            7 -> monthlyPaycheck(null)
            8 -> allMonthlyPaycheck()
            9 -> sortEmployees()
            10 -> nomadEmployees()
            0 -> println("System: Exiting Employee administration...\n")
            !in 4..10 -> println("\t!!! - Not a valid option!\n") // "catch all" solution.
        }
    } while (opt != 0)
}

/**
 * Lists a (1) employee and their data.
 * @param employee - an instance of [Employee]
 */
fun printEmployee(employee: Employee) {
    println("ID-${employee.employeeId} -- name: ${employee.name.firstName} ${employee.name.lastName}")
    println("\tPhone number: ${employee.phoneNumber}")
    println("\tHourly wage: ${employee.hourlyWage}")

    println("\tCurrent work:")
    for (work in employee.work) { // all work information
        println("\t\tDay: ${work.workingDays}")

        val loc = work.location
        if (loc != null) {
            println("\t\tLocation: ${loc.name} - ${loc.description}")
        } else
            println("\t\tLocation: <No location assigned>")
        println("\t\tHours: ${work.startTime} - ${work.endTime}")
        println()
    }
    println()
}

/**
 * Lists all employees in database (list)
 * @see printEmployee
 */
fun listAllEmployees() {
    for (employee in mockEmployee){
        printEmployee(employee)
    }
}

/**
 * Search for employee by first name in database (list)
 * @param name first name of employee function
 * @return Instance of [Employee] or null
 */
fun findEmployee(name: String?): Employee? {
    var name = name
    if (name == null) {
        println("\nSearch for an employee (first name): ")
        print("->")

        name = readln().trim()
    }

    val found = mockEmployee.firstOrNull{it.name.firstName == name}
    if (found != null) {
        return found
     } else {
        return null
     }
}

/**
 * Prints one specific employee and their info
 * @param employee first name of employee
 * @see findEmployee
 * @see printEmployee
 */
fun printOneEmployee(employee: String?) {
    val employee = findEmployee(employee)
    if (employee != null) {
        printEmployee(employee)
    } else{
        println("Could not print ghost..")
    }
}

/**
 * Calculate and display monthly paycheck of an employee
 * Assumption: There are 4 weeks of work in a month
 * @param employee an instance of [Employee]
 * @see findEmployee
 */
fun monthlyPaycheck(employee: Employee?) {
    //val employee = findEmployee(employee?.name?.firstName)

    if (employee != null) {
        val payByHour = employee.hourlyWage
        var workTime= 0
        for (work in employee.work) {
            var dayTime = 0
            val hour = work.endTime.hour - work.startTime.hour
            val minute = (work.startTime.minute - work.startTime.minute) / 60 // converting to hour.
            dayTime = hour + minute
            workTime += dayTime
        }

        val totalPay = workTime * payByHour * 4 // assumption of 4 weeks of work in a month
        println("Monthly pay for ${employee.name.firstName} ${employee.name.lastName}: $totalPay")
    }
}

/**
 * Shows monthly paychecks for all employees
 * @see monthlyPaycheck
 */
fun allMonthlyPaycheck() {
   for (employee in mockEmployee){
       monthlyPaycheck(employee)
   }
    println()
}

/**
 * Sorts employees by their last name
 * @see printOneEmployee
 */
fun sortEmployees() {
    val sortedList= mockEmployee.sortedBy{it.name.lastName}
    println("\nSorted employees:")
    sortedList.forEach{printOneEmployee(it.name.firstName)}
}

/**
 * Lists all employees that don't hava a location for a given work (nomads)
 */
fun nomadEmployees() {
    for (employee in mockEmployee){
       for (work in employee.work){
           if (work.location == null){
               println("${employee.name.firstName} ${employee.name.lastName} is missing a location for ${work.workingDays} (time: ${work.startTime}-${work.endTime})")
           }
       }
    }
}