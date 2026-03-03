@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.calculatrice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatriceApp()
        }
    }
}

@Composable
fun CalculatriceApp() {

    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("Le résultat s'affichera ici") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calculatrice") },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Settings, contentDescription = "Paramètres")
                    }
                }
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                // 🔢 TextFields
                OutlinedTextField(
                    value = number1,
                    onValueChange = { number1 = it },
                    label = { Text("Entrer le premier numéro") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.padding(8.dp)
                )

                OutlinedTextField(
                    value = number2,
                    onValueChange = { number2 = it },
                    label = { Text("Entrer le deuxième numéro") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.padding(8.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                // 📢 Message résultat
                Text(
                    text = message,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    Button(onClick = {
                        calcul(number1, number2, "-") { message = it }
                    }) {
                        Text("-")
                    }

                    Button(onClick = {
                        calcul(number1, number2, "+") { message = it }
                    }) {
                        Text("+")
                    }

                    Button(onClick = {
                        calcul(number1, number2, "/") { message = it }
                    }) {
                        Text("/")
                    }

                    Button(onClick = {
                        calcul(number1, number2, "*") { message = it }
                    }) {
                        Text("*")
                    }
                }
            }
        }
    }
}

// 🔢 Fonction de calcul
fun calcul(n1: String, n2: String, operation: String, onResult: (String) -> Unit) {

    val num1 = n1.toDoubleOrNull()
    val num2 = n2.toDoubleOrNull()

    if (num1 == null || num2 == null) {
        onResult("Veuillez entrer des nombres valides")
        return
    }

    val result = when (operation) {
        "-" -> num1 - num2
        "+" -> num1 + num2
        "*" -> num1 * num2
        "/" -> if (num2 != 0.0) num1 / num2 else {
            onResult("Division par zéro impossible")
            return
        }
        else -> 0.0
    }

    val operationName = when (operation) {
        "-" -> "soustraction"
        "+" -> "addition"
        "*" -> "multiplication"
        "/" -> "division"
        else -> ""
    }

    onResult("Le résultat de $operationName est : $result")
}