package functions

import Observation
import IdentificationStatus
import mockObservation
import mockLocation
import mockMineral
import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 * Prints user choices in observation menu
 */
fun printObservationMenu(){
    println("--Mineral Administration ---\n" +
            "\t1 - List all finds\n" +
            "\t2 - Add find\n" +
            "\t3 - remove find\n" +
            "\t0 - return to main menu\n"
    )
}

/**
 * The main menu function of Observation
 * @see printObservationMenu
 * @see listAllObservations
 * @see addObservation
 * @see deleteObservation
 */
fun observationMenu(){
    var opt : Int?
    do {
        printObservationMenu()
        print("Choose a valid option: ")
        opt = readln().toIntOrNull()
        if (opt == null) {
            println("\t!!! - Please enter an integer!\n" + "\tSystem: Returning to Mineral Administration menu\n")
            continue
        }
        when (opt) {
            1 -> listAllObservations()
            2 -> addObservation()
            3 -> deleteObservation()
            0 -> println("System: Returning to main manu....\n")
            !in 1..3 -> println("\t!!! - Not a valid option!\n") // "catch all" solution.
        }
    } while (opt != 0)
}


/**
 * Lists one observation
 * @param observation - an instance of [Observation]
 */
fun listOneObservation(observation: Observation){
    println("\tLocation: "+ observation.location?.name)
    println("\tMineral: " + observation.mineral?.name)
    println("\tNotes: " + observation.notes)
    println("\tObservation time: " + observation.observedAt?.format(DateTimeFormatter.ofPattern("HH:mm")).toString()) // TODO should ideally include teh date as well.
    println("\tObserved by: " + observation.observedBy?.name?.firstName)
    println("\tStatus: " + observation.status?.displayName)
    println()
}

/**
 * Lists all observations
 * @see listOneObservation
 */
fun listAllObservations(){
    for (finds in mockObservation){
        listOneObservation(finds)
    }
}
/**
 * Adds observation of geological find
 * @see findLocation
 * @see findMineral
 * @see findEmployee
 */
fun addObservation(){
    println("\n--- Add Find ---")
    
    // Location prompt
    print("Valid locations: ")
    for (location in mockLocation){
        print(location.name + " ")
    }
    println()
    print("What location are you at?: ")
    val location = readln().trim()
    val foundLocation = findLocation(location)
    if (foundLocation == null){
        println("location not found")
    }

    // Mineral prompt
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

    // Status prompt
    println()
    print("Valid status: ")
    for (enum in IdentificationStatus.entries){
        print("${enum.displayName} ")
    }
    println()

    print("How sure are you of this information?: ")
    val statusInput = readln().trim()

    //  Match against the enum names (case-insensitive!)
    val validStatus: IdentificationStatus =
        IdentificationStatus.entries.firstOrNull { it.name.equals(statusInput, ignoreCase = true) }
            ?: run {
                println("Status not found: Default to UNDEFINED")
                IdentificationStatus.UNDEFINED
            }

    // Notes prompt
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

    // Add all values to a new Finds
    val newObservation = Observation (
        location = foundLocation,
        mineral = foundMineral,
        status = validStatus,
        notes = notes,
        observedAt = LocalTime.now(),
        observedBy = foundEmployee,
    )
    mockObservation.add(newObservation) // adds to list of findings
}

/**
 * Deletes an observation that the user inputs
 */
fun deleteObservation(){
    println("\n--- Remove Find ---")
    println("Valid Choices: ")

    var index = 1
    for (finds in mockObservation){
        println("\t$index - ${finds.location?.name} - ${finds.mineral?.name} - ${finds.observedAt?.format(DateTimeFormatter.ofPattern("HH:mm"))} - ${finds.observedBy?.name?.firstName} - ${finds.status?.displayName} - ${finds.notes}")
        index++
    }
    print("----------------- ID of location to be deleted: ")
    val choice = readln().toIntOrNull()
    if (choice != null) {
        mockObservation.removeAt(choice-1)
    } else {
        println("--- Something went wrong, nothing is deleted. ---\n")
    }
}
