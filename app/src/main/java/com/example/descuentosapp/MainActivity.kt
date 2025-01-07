package com.example.descuentosapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.descuentosapp.ui.theme.DescuentosAppTheme
import com.example.descuentosapp.viewModel.CalcularViewModel1
import com.example.descuentosapp.viewModel.CalcularViewModel2
import com.example.descuentosapp.viewModel.CalcularViewModel3
import com.example.descuentosapp.views.HomeView
import com.example.descuentosapp.views.HomeView2
import com.example.descuentosapp.views.HomeView3

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: CalcularViewModel3 by viewModels()
        enableEdgeToEdge()
        setContent {
            DescuentosAppTheme{
                HomeView3(viewModel)
            }
        }
    }
}