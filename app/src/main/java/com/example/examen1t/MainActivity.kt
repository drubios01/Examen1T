package com.example.examen1t

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.examen1t.ui.theme.Examen1TTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Examen1TTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ExamenApp()
                }
            }
        }
    }
}

@Composable
fun ExamenApp() {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf(0) }
    var lugarNacimento by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar()
        }
    ) { it ->
        Column(
            modifier = Modifier
                .padding(bottom = 40.dp, start = 40.dp, end = 40.dp)
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.welcome),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(alignment = Alignment.Start),
            )
            EditTextField(R.string.input_name) { nombre = it }
            EditTextField(R.string.input_surname) { apellido = it }
            EditNumberField(R.string.input_age) { edad = it }
            EditTextField(R.string.input_birthplace) { lugarNacimento = it }

            val formattedText = formatText(nombre, apellido, edad, lugarNacimento)
            Text(
                text = formattedText,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name)
            )
        },
        modifier = modifier
    )
}

@Composable
fun EditTextField(label: Int, onValueChange: (String) -> Unit) {
    var value by remember { mutableStateOf("") }

    TextField(
        value = value,
        onValueChange = {
            value = it
            onValueChange(it)
        },
        label = { Text(stringResource(label)) },
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun EditNumberField(@StringRes label: Int, onValueChange: (Int) -> Unit) {
    var number by remember { mutableStateOf(0) }
    TextField(
        value = number.toString(),
        onValueChange = {
            if (it.isNotEmpty()) {
                number = it.toInt()
                onValueChange(number)
            }
        },
        label = { Text(stringResource(id = label)) },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        modifier = Modifier.padding(16.dp)
    )
}

fun formatText(number: String, apellido: String, edad: Int, lugarNacimento: String): String {
    return "$number $apellido tiene $edad años y ha nacido en $lugarNacimento"
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Examen1TTheme {
        ExamenApp()
    }
}