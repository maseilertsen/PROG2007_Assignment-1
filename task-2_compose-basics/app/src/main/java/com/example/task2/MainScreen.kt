package com.example.task2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen(modifier: Modifier = Modifier){
    Scaffold (
        modifier = Modifier.fillMaxSize()
    )
    { innerPadding -> HomeScreen(modifier = modifier.padding(innerPadding))

    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .fillMaxSize()
        .background(Color(0xFF4779DC)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello there",
            fontSize = 40.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        ) // end column
        Row (
            modifier = Modifier.padding(20.dp)
        )
        {
            var text by remember { mutableStateOf("") }

            TextField(
                modifier = Modifier,
                placeholder = {Text("hello")},
                value = text,
                onValueChange = { /*TODO*/ },
            )
            Button(
                modifier = Modifier,
                onClick = {/*TODO*/},
                shape = ButtonDefaults.shape
            ) {
                Text(
                text = "Save",
                fontSize = 20.sp,
                color = Color.LightGray
            ) }
        } // end row
    }
}
