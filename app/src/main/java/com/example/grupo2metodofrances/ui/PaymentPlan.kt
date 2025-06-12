package com.example.grupo2metodofrances.ui


import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.grupo2metodofrances.R
import kotlin.math.pow


data class TermData (
    var tep: Double,
    var capital: Double,
    var initial: Double,
    var term: Int
)

@SuppressLint("NewApi")
@Composable
fun PaymentPlan(_tea: Double,
                _capital: Double, _frec: Int, _years: Int,modifier: Modifier = Modifier){
    val tep = ((1+(_tea/100)).pow((_frec/360.0))-1)*100;
    val capital = _capital;
    val term = (360/_frec)*_years;
    val data = mutableListOf(TermData(tep,capital,capital,term))
    Scaffold(
        modifier = Modifier
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .defaultMinSize(
                    minHeight = 150.dp // we have a min height for the container, it's flexible
                )
                .background(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(size = 4.dp)
                )
        ) {

            Image( // image with flexible height as a background
                painter = painterResource(id = R.drawable.fondo_cuadriculado),
                contentDescription = "Grid",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
            )
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Datos",
                    color = Color.Black,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Capital: $capital",
                )
                Text(
                    text = "Tasa Efectiva: " + _tea.toString(),
                )
                Text(
                    text = "Plazos: " + term.toString(),
                )
                var i = 0;
                while (i < term) {
                    val a = TermI(data.get(i), i);
                    data.addLast(a);
                    i = i + 1;
                }

            }
        }
    }
}

@Composable
fun TermI(
    info: TermData,
    i: Int
):TermData {
    val inst = info.initial * ((info.tep/100)*(1+(info.tep/100.0)).pow(info.term))/((1+(info.tep/100)).pow(info.term)-1);
    val interest = info.capital*(info.tep/100);
    val amort = inst - interest;
    var finalP = info.capital - amort;
    if (finalP < 0){finalP = 0.00;}
    val i2 = i+1;
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Plazo " + i2.toString(),
                color = Color.Black,
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    modifier = Modifier.background(Color.LightGray)
                ) {
                    Text(
                        text = "TEP: ",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .border(border = BorderStroke(2.dp, Color.Black))
                            .padding(10.dp)
                    )
                }
                Box(
                    modifier = Modifier.background(Color.White)
                ) {
                    Text(
                        text = info.tep.toString(),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .border(border = BorderStroke(2.dp, Color.Black))
                            .padding(10.dp)
                    )
                }
            }
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    modifier = Modifier.background(Color.LightGray)
                ) {
                    Text(
                        text = "Saldo Inicial: ",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .border(border = BorderStroke(2.dp, Color.Black))
                            .padding(10.dp)
                    )
                }
                Box(
                    modifier = Modifier.background(Color.White)
                ) {
                    Text(
                        text = String.format("%.2f", info.capital),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .border(border = BorderStroke(2.dp, Color.Black))
                            .padding(10.dp)
                    )
                }
            }

            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.background(Color.LightGray)
                ) {
                    Text(
                        text = "Cuota: ",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .border(border = BorderStroke(2.dp, Color.Black))
                            .padding(10.dp)
                    )
                }
                Box(
                    modifier = Modifier.background(Color.White)
                ) {
                    Text(
                        text = String.format("%.2f", inst),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .border(border = BorderStroke(2.dp, Color.Black))
                            .padding(10.dp)
                    )
                }
            }
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.background(Color.LightGray)
                ) {
                    Text(
                        text = "Interés Mensual: ",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .border(border = BorderStroke(2.dp, Color.Black))
                            .padding(10.dp)
                    )
                }
                Box(
                    modifier = Modifier.background(Color.White)
                ) {
                    Text(
                        text = String.format("%.2f", interest),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .border(border = BorderStroke(2.dp, Color.Black))
                            .padding(10.dp)
                    )
                }
            }
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.background(Color.LightGray)
                ) {
                    Text(
                        text = "Amortización: ",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .border(border = BorderStroke(2.dp, Color.Black))
                            .padding(10.dp)
                    )
                }
                Box(
                    modifier = Modifier.background(Color.White)
                ) {
                    Text(
                        text = String.format("%.2f", amort),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .border(border = BorderStroke(2.dp, Color.Black))
                            .padding(10.dp)
                    )
                }
            }
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.background(Color.LightGray)
                ) {
                    Text(
                        text = "Saldo Final: ",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .border(border = BorderStroke(2.dp, Color.Black))
                            .padding(10.dp)
                    )
                }
                Box(
                    modifier = Modifier.background(Color.White)
                ) {
                    Text(
                        text = String.format("%.2f", finalP),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .border(border = BorderStroke(2.dp, Color.Black))
                            .padding(10.dp)
                    )
                }
            }
        }

    }
    return TermData(info.tep,finalP,info.initial,info.term);
}