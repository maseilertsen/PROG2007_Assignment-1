package functions

import Finds
import IdentificationStatus
import mockFinds
import mockLocation
import mockMineral
import java.time.LocalTime
import java.time.format.DateTimeFormatter


fun printFindsMenu(){
    println("--Mineral Administration ---\n" +
            "\t1 - List all finds\n" +
            "\t2 - Add find\n" +
            "\t3 - remove find\n" +
            "\t0 - return to main menu\n"
    )
}

fun findsMenu(){
    var opt : Int?
    do {
        printFindsMenu()
        print("Choose a valid option: ")
        opt = readln().toIntOrNull()
        if (opt == null) {
            println("\t!!! - Please enter an integer!\n" + "\tSystem: Returning to Mineral Administration menu\n")
            continue
        }
        when (opt) {
            1 -> listAllFinds()
            2 -> addFind()
            3 -> deleteFind()
            0 -> println("System: Returning to main manu....\n")
            !in 1..3 -> println("\t!!! - Not a valid option!\n") // "catch all" solution.
        }
    } while (opt != 0)
}


/**
 * Lists one find
 */
fun listOneFind(finds: Finds){
    println("\tLocation: "+ finds.location?.name)
    println("\tMineral: " + finds.mineral?.name)
    println("\tNotes: " + finds.notes)
    println("\tObservation time: " + finds.observedAt?.format(DateTimeFormatter.ofPattern("HH:mm")).toString()) // TODO should ideally include teh date as well.
    println("\tObserved by: " + finds.observedBy?.name?.firstName)
    println("\tStatus: " + finds.status?.displayName)
    println()
}
fun listAllFinds(){
    for (finds in mockFinds){
        listOneFind(finds)
    }
}
/**
 * Adds find of geological data.
 */
fun addFind(){
    println("\n--- Add Find ---")
    
    // Find location
    print("Valid locations: ")
    for (location in mockLocation){
        print(location.name + " ")
    }
    println()
    print("What location are you at?: ")
    val location = readln().trim()
    var foundLocation = findLocation(location)
    if (foundLocation == null){
        println("location not found")
    }

    // Mineral
    println()
    print("Valid minerals: ")
    for (mineral in mockMineral)
        print(mineral.name + " ")
    println()

    print("What mineral did you find?: ")
    val mineral = readln().trim()
    val foundMineral = findMineral(mineral)
    if (foundMineral == null){
        println("mineral not found")
    }

    // Status
    println()
    print("Valid status: ")
    for (enum in IdentificationStatus.entries){
        print("${enum.displayName} ")
    }
    println()

    print("How sure are you of this information?: ")
    val statusInput = readln().trim()

    // Try to match against the enum names (case-insensitive)
    val validStatus: IdentificationStatus =
        IdentificationStatus.entries.firstOrNull { it.name.equals(statusInput, ignoreCase = true) }
            ?: run {
                println("Status not found: Default to UNDEFINED")
                IdentificationStatus.UNDEFINED
            }

    // Notes
    println()
    print("Add any notes?\n" +
            "->")
    val notes = readln().trim()

    println()
    print("Who was the observer?: ")
    val observer = readln().trim()
    val foundEmployee = findEmployee(observer)
    if (foundEmployee == null){
        println("employee not found")
    }

    val newFind = Finds(
        location = foundLocation,
        mineral = foundMineral,
        status = validStatus,
        notes = notes,
        observedAt = LocalTime.now(),
        observedBy = foundEmployee,
    )
    mockFinds.add(newFind) // adds to list of findings
}

fun deleteFind(){
    println("\n--- Remove Find ---")
    println("Valid Choices: ")

    var index = 1
    for (finds in mockFinds){
        println("\t$index - ${finds.location?.name} - ${finds.mineral?.name} - ${finds.observedAt?.format(DateTimeFormatter.ofPattern("HH:mm"))} - ${finds.observedBy?.name?.firstName} - ${finds.status?.displayName} - ${finds.notes}")
        index++
    }
    print("----------------- ID of location to be deleted: ")
    val choice = readln().toIntOrNull()
    if (choice != null) {
        mockFinds.removeAt(choice-1)
    } else {
        println("--- Something went wrong, nothing is deleted. ---\n")
    }
}
