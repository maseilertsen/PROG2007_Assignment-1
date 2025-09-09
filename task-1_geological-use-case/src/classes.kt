import java.time.DayOfWeek
import java.time.LocalTime

/**
 * Data of a Geological Location
 */
data class Location(
    val name: String,
    val description: String = "",
    val coordinates: GeoPoint,
    val notes: String = ""
    )

/**
 * Latitude and longitude of location
 * @see Location
 */
data class GeoPoint(
    val latitude: Double,
    val longitude: Double
)

/**
 * Data of Assignments for geological company
 */
data class Assignment(
    val employee: Employee,
    val location: Location,
    val dayOfWeek: DayOfWeek,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val hourlyWage: Double,
    )

/**
 * Data of Employees
 */
data class Employee (
    val employeeId: Int,
    val name: String,
    val phoneNumber: String
)

/**
 * Location of a found mineral
 */
data class LocationMineral(
    var location: Location, // can change if needed.
    var mineral: Mineral
)

/**
 * Mineral properties
 */
data class Mineral(val name: String,
    val luster: Luster,
    val color: Color,
    val hardness: Hardness,
    val fracture: Fracture,
    )

/**
 * Range of hardness for a mineral
 */
data class Hardness (
    val min: Double, // >= 1.0
    val max: Double  // <= 10.0 && max >= min
)