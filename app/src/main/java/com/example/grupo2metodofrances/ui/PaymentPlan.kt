package com.example.grupo2metodofrances.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.w3c.dom.Text
import kotlin.math.pow

@Composable
fun PaymentPlan(_tep: Double,
                _capital: Double, _term: Int, modifier: Modifier = Modifier){
    val tep = _tep;
    val capital = _capital;
    val term = _term;
    val inst = capital * ((tep/100)*(1+(tep/100.0)).pow(term))/((1+(tep/100)).pow(term)-1);
    val interest = capital*(tep/100);
    val amort = inst - interest;
    val finalP = capital - amort;
    Scaffold(
        modifier = Modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Datos",
            )
            Text(
                text = "Capital: $capital",
            )
            Text(
                text = "Tasa Efectiva: " + tep.toString(),
            )
            Text(
                text = "Plazos: " + term.toString(),
            )
            Text(
                text = "Plazo 1",
            )
            Text(
                text = "Cuota: " + inst.toString(),
            )
            Text(
                text = "Interés Mensual: " + interest.toString(),
            )
            Text(
                text = "Amortización: " + amort.toString(),
            )
            Text(
                text = "Saldo Final: " + finalP.toString()
            )
        }
    }
}
