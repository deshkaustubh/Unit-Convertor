package com.example.project1unitconvertor

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project1unitconvertor.ui.theme.Project1UnitConvertorTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Project1UnitConvertorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConvertor()
                }
            }
        }
    }
}

@Composable
fun UnitConvertor() {

    var inputValue by remember { mutableStateOf(value = "") }
    var outputValue by remember { mutableStateOf(value = "")}
    var inputUnit by remember { mutableStateOf(value = "Meters")}
    var outputUnit by remember { mutableStateOf(value = "Meters")}
    var iExpanded by remember { mutableStateOf(value = false)}
    var oExpanded by remember { mutableStateOf(value = false)}
    val conversionFactor = remember { mutableStateOf(value = 1.00)}
    val oConversionFactor = remember { mutableStateOf(value = 1.00)}

    fun convertUnits(){
        //  ?: - elvis operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value *100.0 / oConversionFactor.value).roundToInt()/100
        outputValue = result.toString()
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //Here all UI elements will be stacked below each other
        Text(text = "Unit Convertor",style = MaterialTheme.typography.headlineLarge ,modifier = Modifier.padding(10.dp))
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
            inputValue = it
            convertUnits()
            //Here goes what should happen, when the value fo our OutlinedTextField changes
        },
        label = { Text(text = "Enter Value (Default Display Text)")}    )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //Here all UI elements will be stacked next to each other
            Box {
                Button(onClick = { iExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimeters"
                            conversionFactor.value = 0.01
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text(text = "Meters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meters"
                            conversionFactor.value = 1.0
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text(text = "Millimeters") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Millimeters"
                            conversionFactor.value = 0.001
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text(text = "Feet")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.value = 0.3048
                            convertUnits()
                        })
                }
                }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { oExpanded = true}) {
                        Text(text = outputUnit)
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = "Arrow Down"
                        )
                    DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false}) {
                        DropdownMenuItem(text = { Text(text = "Centimeters")},
                            onClick = {
                                oExpanded = false
                                outputUnit = "Centimeters"
                                oConversionFactor.value = 0.01
                                convertUnits()
                            })
                        DropdownMenuItem(text = { Text(text = "Meters")},
                            onClick = {
                                oExpanded = false
                                outputUnit = "Meters"
                                oConversionFactor.value = 1.00
                                convertUnits()
                            })
                        DropdownMenuItem(text = { Text(text = "Millimeters") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "Millimeters"
                                oConversionFactor.value = 0.001
                                convertUnits()
                            })
                        DropdownMenuItem(text = { Text(text = "Feet")},
                            onClick = {
                                oExpanded = false
                                outputUnit = "Feet"
                                conversionFactor.value = 0.3048
                                convertUnits()
                            })
                        }
                    }
                }
            }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result: ${outputValue} ${outputUnit}",
            style = MaterialTheme.typography.headlineMedium)
        }

        fun Icon() {
            TODO("Not yet implemented")
        }


    }
@Preview(showBackground = true)
@Composable
fun UnitConvertorPreview() {
    UnitConvertor()
}