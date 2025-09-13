import java.time.DayOfWeek
import java.time.LocalTime

/**
 * Data of a Geological Location
 * @see GeoPoint
 * TODO should notes (and descriptions) be restricted in length?
 */
data class Location(
    val name: String,
    val description: String = "",
    val coordinates: GeoPoint,
    val notes: String = ""
    )

/**
 * Latitude and longitude of location
 */
data class GeoPoint(
    val latitude: Double,
    val longitude: Double
) {
    init {
        // Will crash the program if wrong input is entered...
        require(latitude in -90.0..90.0) { "Latitude must be between -90 and 90, was $latitude" }
        require(longitude in -180.0..180.0) { "Longitude must be between -180 and 180, was $longitude" }
    }
}

/**
 * Data of Assignments for geological company
 * @see Employee
 * @see Location
 * @see DayOfWeek
 * @see LocalTime
 * @see LocalTime
 */
data class Work (
    val workingDays: DayOfWeek,
    val location: Location,
    val startTime: LocalTime,
    val endTime: LocalTime,
    )

/**
 * Data of Employees
 * TODO: could I if employeeId already exists with a require?
 */
data class Employee (
    val employeeId: Int,
    val name: Name,
    val phoneNumber: String,
    var hourlyWage: Double,
    val work: MutableList<Work>
){
    init {
        require(hourlyWage >= 0) { "Hourly Wage must be at least 0." }
    }
}

/**
 * Name of employee
 * @see Employee
 */
data class Name(
    val firstName: String,
    val lastName: String
)

/**
 * Location of a found mineral.
 * All variables can be easily modified for ease of use.
 * @see Location
 * @see Mineral
 */
data class LocationMineral(
    var location: Location,
    var mineral: Mineral,
    var status: IdentidicationStatus,
    var notes: String = "",
    var observedAt: LocalTime? = null
)

/**
 * Mineral properties
 * @see Luster
 * @see Color
 * @see Hardness
 * @see Fracture
 */
 data class Mineral(
    val name: String,
    val luster: Luster,
    val color: Color,
    val hardness: Hardness,
    val fracture: Fracture,
    )

/**
 * Range of hardness for a mineral
 * If only one value is passed (no range) max will be null.
 */
data class Hardness (
    val min: Double,            // >= 1.0
    val max: Double? = null     // <= 10.0 && max >= min
){
    init {
        if (max != null) {
            require(max >= min) { "Max hardness  must be greater than min: $min" }
        }
        require(min >= 0.0) { "Hardness must be at least $min" }
    }
}