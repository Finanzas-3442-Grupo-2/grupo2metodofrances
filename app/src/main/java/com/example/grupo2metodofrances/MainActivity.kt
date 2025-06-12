package com.example.grupo2metodofrances

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.grupo2metodofrances.ui.PaymentPlan
import com.example.grupo2metodofrances.ui.theme.Grupo2metodofrancesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Lo de abajo es lo que se pega
        setContent {
            Grupo2metodofrancesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PaymentPlan(
                        _tea = 25.00,
                        _capital = 1440000.00,
                        _frec = 30,
                        _years = 4,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}