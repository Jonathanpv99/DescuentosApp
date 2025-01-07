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
import com.example.descuentosapp.viewModel.CalcularViewModel2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView2(viewModel2: CalcularViewModel2){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "App Descuentos", color = Color.White) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor =    MaterialTheme.colorScheme.primary
                )
            )
        }) {
        ContentHomeView2(it, viewModel2)
    }
}

@Composable
fun ContentHomeView2(paddingValues: PaddingValues, viewModel2: CalcularViewModel2){
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(10.dp)
            .fillMaxSize(),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TwoCards(
            title1 = "Total",
            number1 = viewModel2.totalDescuento,
            title2 =  "Descuento",
            number2 = viewModel2.precioDescuento
        )
        MainTextField(
            values = viewModel2.precio,
            onValueChanges = {viewModel2.onValue(it, "precio")},
            label = "Precio"
        )
        SpacerH()
        MainTextField(
            values = viewModel2.descuento,
            onValueChanges = {viewModel2.onValue(it, "descuento")},
            label = "Descuento"
        )
        SpacerH(10.dp)
        MainButton(text = "Generar Descuento") {
            viewModel2.calcular()
        }
        SpacerH()
        MainButton(text = "Limpiar", color = Color.Red) {
            viewModel2.lipiar()
        }

        if(viewModel2.viewAlert){
            Alert(
                title = "Alerta",
                message = "Los Campos de precio y/ó descuento no pueden ir vacios",
                confirmText = "Aceptar",
                onConfirmClick = { viewModel2.cancelAlert()},
                onDismissClick = {false}
            )
        }
    }
}