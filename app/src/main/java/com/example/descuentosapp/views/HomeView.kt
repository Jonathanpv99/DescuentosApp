package com.example.descuentosapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.descuentosapp.components.Alert
import com.example.descuentosapp.components.MainButton
import com.example.descuentosapp.components.MainTextField
import com.example.descuentosapp.components.SpacerH
import com.example.descuentosapp.components.TwoCards
import com.example.descuentosapp.viewModel.CalcularViewModel1
import com.example.descuentosapp.viewModel.CalcularViewModel2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(viewModel1: CalcularViewModel1){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "App Descuentos", color = Color.White) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor =    MaterialTheme.colorScheme.primary
                )
            )
        }) {
        ContentHomeView(it, viewModel1)
    }
}

@Composable
fun ContentHomeView(paddingValues: PaddingValues, viewModel1: CalcularViewModel1){
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(10.dp)
            .fillMaxSize(),
            //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var precio by remember { mutableStateOf("") }
        var descuento by remember { mutableStateOf("") }

        var precioDescuento by remember { mutableStateOf(0.0) }
        var totalDescuento by remember { mutableStateOf(0.0) }

        var viewAlert by remember { mutableStateOf(false) }
        TwoCards(
            title1 = "Total",
            number1 = totalDescuento,
            title2 =  "Descuento",
            number2 = precioDescuento
        )
        MainTextField(
            values = precio,
            onValueChanges = {precio = it},
            label = "Precio"
        )
        SpacerH()
        MainTextField(
            values = descuento,
            onValueChanges = {descuento = it},
            label = "Descuento"
        )
        SpacerH(10.dp)
        MainButton(text = "Generar Descuento") {
            val result = viewModel1.calcular(precio,descuento)
            viewAlert = result.second.second
            if(!viewAlert){
                precioDescuento = result.first
                totalDescuento = result.second.first
            }
        }
        SpacerH()
        MainButton(text = "Limpiar", color = Color.Red) {
            precio = ""
            descuento = ""
            precioDescuento = 0.0
            totalDescuento = 0.0
        }

        if(viewAlert){
            Alert(
                title = "Alerta",
                message = "Los Campos de precio y/ó descuento no pueden ir vacios",
                confirmText = "Aceptar",
                onConfirmClick = {viewAlert = false},
                onDismissClick = {viewAlert = false}
            )
        }
    }
}