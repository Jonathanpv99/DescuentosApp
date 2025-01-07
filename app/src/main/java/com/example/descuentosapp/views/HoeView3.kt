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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.descuentosapp.components.Alert
import com.example.descuentosapp.components.MainButton
import com.example.descuentosapp.components.MainTextField
import com.example.descuentosapp.components.SpacerH
import com.example.descuentosapp.components.TwoCards
import com.example.descuentosapp.viewModel.CalcularViewModel2
import com.example.descuentosapp.viewModel.CalcularViewModel3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView3(viewModel3: CalcularViewModel3){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "App Descuentos", color = Color.White) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor =    MaterialTheme.colorScheme.primary
                )
            )
        }) {
        ContentHomeView3(it, viewModel3)
    }
}

@Composable
fun ContentHomeView3(paddingValues: PaddingValues, viewModel3: CalcularViewModel3){
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(10.dp)
            .fillMaxSize(),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val state = viewModel3.state
        TwoCards(
            title1 = "Total",
            number1 = state.totalDescuento,
            title2 =  "Descuento",
            number2 = state.precioDescuento
        )
        MainTextField(
            values = state.precio,
            onValueChanges = {viewModel3.onValue(it, "precio")},
            label = "Precio"
        )
        SpacerH()
        MainTextField(
            values = state.descuento,
            onValueChanges = {viewModel3.onValue(it, "descuento")},
            label = "Descuento"
        )
        SpacerH(10.dp)
        MainButton(text = "Generar Descuento") {
            viewModel3.calcular()
        }
        SpacerH()
        MainButton(text = "Limpiar", color = Color.Red) {
            viewModel3.lipiar()
        }

        if(state.viewAlert){
            Alert(
                title = "Alerta",
                message = "Los Campos de precio y/ó descuento no pueden ir vacios",
                confirmText = "Aceptar",
                onConfirmClick = { viewModel3.cancelAlert()},
                onDismissClick = {false}
            )
        }
    }
}