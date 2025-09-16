package com.example.task2

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        HomeScreen(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF4779DC))
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 24.dp)
        )
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    var textName by remember { mutableStateOf("") }
    var textDesc by remember { mutableStateOf("") }
    var textLat by remember { mutableStateOf("") }
    var textLong by remember { mutableStateOf("") }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "newLocation()",
            fontSize = 40.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            modifier = Modifier.padding(20.dp)
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Name") },
            value = textName,
            onValueChange = { textName = it},
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextField(
                modifier = Modifier.weight(1f),
                placeholder = { Text("Latitude") },
                value = textLat,
                onValueChange = { textLat = it },
            )
            TextField(
                modifier = Modifier.weight(1f),
                placeholder = { Text("Longitude") },
                value = textLong,
                onValueChange = { textLong = it },
            )
        } // end row
        Row (
            modifier = Modifier.fillMaxWidth()
            ) {


            TextField(
                modifier = Modifier,
                placeholder = {Text("Description")},
                value = textDesc,
                onValueChange = { textDesc = it },
            )
            Button(
                modifier = Modifier.fillMaxWidth()
                    .padding(5.dp,5.dp),
                onClick = {
                            val newLocation = Location(textName, textDesc, GeoPoint(textLat.toDouble(), textLong.toDouble()))

                            mockLocation.add(newLocation)

                },
            ) {
                Text(
                text = "Save",
                fontSize = 20.sp,
                color = Color.LightGray
            ) }
        } // end row
        Text(text = "Saved Locations:",
                  fontSize = 20.sp,
                  fontWeight = FontWeight.SemiBold,
                  color = Color.White,
        )

        SimpleVerticalList(
            items = mockLocation
        )
    } // end column
}




data class Location(
    val name: String,
    val description: String = "",
    val coordinates: GeoPoint,
    val notes: String = ""
)

data class GeoPoint(
    val latitude: Double,
    val longitude: Double
)

var mockLocation = mutableStateListOf(
    Location("Cairo", "A bit sandy",GeoPoint(30.0444, 31.2357)),   // Egypt
    Location("Oslo", "Surrounded by fjords and forests", GeoPoint(59.9139, 10.7522), "Does anyone really live here?"),   // Norway
)

@Composable
fun SimpleVerticalList(items: MutableList<Location>) {

    Column {
        items.forEach { item ->
            Row (
                modifier = Modifier.padding(0.dp,10.dp)
                    .background((Color.LightGray))
                    
            ){
                Text(
                    fontWeight = FontWeight.SemiBold,
                    text = "${item.name} ")
                Text("${item.description} (${item.coordinates.latitude},${item.coordinates.longitude})", modifier = Modifier.padding(8.dp))
                }
        }
    }
}