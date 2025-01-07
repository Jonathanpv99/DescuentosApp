package com.example.descuentosapp.viewModel

import androidx.lifecycle.ViewModel

class CalcularViewModel1 : ViewModel() {

    fun calcular(price: String, discount: String): Pair<Double, Pair<Double, Boolean>>{
        var precioDescuento: Double = 0.0
        var totalDescuento: Double = 0.0
        var viewAlert = false

        if(price != "" && discount != ""){
            println("Entro sin datooooos")
            precioDescuento = CalculatePrice(price.toDouble(), discount.toDouble())
            totalDescuento =  CalculateDiscount(price.toDouble(), discount.toDouble())
        }else{
            viewAlert = true
        }
        return Pair(precioDescuento, Pair(totalDescuento, viewAlert))
    }

    private fun CalculatePrice(price: Double, discount: Double): Double {
        val res = price - CalculateDiscount(price, discount)
        return kotlin.math.round(res * 100) / 100.0
    }

    private fun CalculateDiscount(price: Double, discount: Double): Double {
        val res: Double = price * (1 - discount / 100)
        return kotlin.math.round(res * 100) / 100.0
    }
}