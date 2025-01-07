package com.example.descuentosapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.descuentosapp.model.CalcularState

class CalcularViewModel3 : ViewModel() {
    var state by mutableStateOf(CalcularState())
        private set

    fun onValue(value: String, text: String) {
        when (text) {
            "precio" -> state = state.copy(precio = value)
            "descuento" -> state = state.copy(descuento = value)
        }
    }

    fun calcular() {
        val precio = state.precio
        val descuento = state.descuento
        if (precio != "" && descuento != "") {
            println("Entro sin datooooos")
            state = state.copy(
                precioDescuento = CalculatePrice(precio.toDouble(), descuento.toDouble()),
                totalDescuento = CalculateDiscount(precio.toDouble(), descuento.toDouble())
            )
        } else {
            state = state.copy(
                viewAlert = true
            )
        }
    }

    private fun CalculatePrice(price: Double, discount: Double): Double {
        val res = price - CalculateDiscount(price, discount)
        return kotlin.math.round(res * 100) / 100.0
    }

    private fun CalculateDiscount(price: Double, discount: Double): Double {
        val res: Double = price * (1 - discount / 100)
        return kotlin.math.round(res * 100) / 100.0
    }

    fun lipiar() {
        state = state.copy(
            precio = "",
            descuento = "",
            precioDescuento = 0.0,
            totalDescuento = 0.0
        )
    }

    fun cancelAlert() {
        state = state.copy(
            viewAlert = false
        )
    }
}