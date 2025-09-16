package com.example.task2

import androidx.compose.runtime.mutableStateListOf
/**
 * Data of a Geological Location
 * @see GeoPoint
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
)

/**
 * Mock data used by program
 */
var mockLocation = mutableStateListOf(
    Location("Cairo", "A bit sandy",GeoPoint(30.0444, 31.2357)),   // Egypt
    Location("Oslo", "Surrounded by fjords and forests", GeoPoint(59.9139, 10.7522), "Does anyone really live here?"),   // Norway
)