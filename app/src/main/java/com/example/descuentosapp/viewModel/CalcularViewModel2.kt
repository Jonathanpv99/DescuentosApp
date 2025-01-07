package com.example.descuentosapp.viewModel
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalcularViewModel2: ViewModel() {

    //sintaxys completa
    /*private val _precio = mutableStateOf("")
    val precio :State<String> = _precio*/

    //sintaxys corta
    var precio by mutableStateOf("")
        private set
    /*fun onValuePrecio(value:String){
        precio = value
    }*/
    /*fun onValueDescuento(value:String){
        descuento = value
    }*/
    fun onValue(value: String, text: String){
        when(text){
            "precio" -> precio = value
            "descuento" -> descuento = value
        }
    }
    var descuento by mutableStateOf("")
        private set
    var precioDescuento by mutableStateOf(0.0)
        private set
    var totalDescuento by mutableStateOf(0.0)
        private set
    var viewAlert by mutableStateOf(false)
        private set

    fun calcular(){

        if(precio != "" && descuento != ""){
            println("Entro sin datooooos")
            precioDescuento = CalculatePrice(precio.toDouble(), descuento.toDouble())
            totalDescuento =  CalculateDiscount(precio.toDouble(), descuento.toDouble())
        }else{
            viewAlert = true
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

    fun lipiar(){
        precio = ""
        descuento = ""
        precioDescuento = 0.0
        totalDescuento = 0.0
    }
    fun cancelAlert(){
        viewAlert = false
    }

}