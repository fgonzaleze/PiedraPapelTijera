package com.example.piedrapapeltijera

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PiedraPapelTijera()
        }
    }
}

@Composable
fun PiedraPapelTijera() {
    val image = painterResource(id = R.drawable.icono)
    var jugador by remember { mutableStateOf("Piedra") }
    var maquina by remember { mutableStateOf("Papel") }
    var puntuaciones by remember { mutableStateOf("0 / 0") }
    var puntuacionJugador by remember { mutableStateOf(0) }
    var puntuacionMaquina by remember { mutableStateOf(0) }

    Column(//Modifier.background(Color.White)
    ) {
        Image(
            painter = image,
            contentDescription = null
        )
        Text(
            text = "Puntuacion", fontSize = 30.sp, modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        )
        Text(
            text = puntuaciones, fontSize = 50.sp, modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )

        Row(modifier = Modifier.padding(top = 85.dp)) {
            Column(
                Modifier
                    .fillMaxWidth(0.5f)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                Playeraction(jugador = "Tu elección", maquina = jugador)
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                Playeraction(jugador = "Elección maquina", maquina = maquina)
            }

        }
        Row(modifier = Modifier.padding(top = 70.dp)) {
            Column(
                Modifier
                    .fillMaxWidth(0.33f)
                    .wrapContentWidth(Alignment.CenterHorizontally)
                // Boton para la piedra
            ) {
                boton(bvlaue = "Piedra") {
                    jugador = "Piedra"
                    maquina = aleatorio()
                    val win = puntuacion(jugador, maquina)
                    if (win == 1)
                        puntuacionJugador++
                    else if (win == 0)
                        puntuacionMaquina++
                    puntuaciones = "$puntuacionJugador / $puntuacionMaquina"
                }
            }
            Column(
                Modifier
                    .fillMaxWidth(0.5f)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                // boton para el Papel
                boton(bvlaue = "Papel") {
                    jugador = "Papel"
                    maquina = aleatorio()
                    val win = puntuacion(jugador, maquina)
                    if (win == 1)
                        puntuacionJugador++
                    else if (win == 0)
                        puntuacionMaquina++
                    puntuaciones = "$puntuacionJugador / $puntuacionMaquina"
                }
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                // Boton tijeras
                boton(bvlaue = "Tijeras") {
                    jugador = "Tijeras"
                    maquina = aleatorio()
                    val win = puntuacion(jugador, maquina)
                    if (win == 1)
                        puntuacionJugador++
                    else if (win == 0)
                        puntuacionMaquina++
                    puntuaciones = "$puntuacionJugador / $puntuacionMaquina"
                }
            }

        }
        Row(
            verticalAlignment = Alignment.Bottom, modifier = Modifier.fillMaxHeight()
        ) {
            Spacer(modifier = Modifier.weight(1f))
            var texto = "Javier Gonzalez Eslava"
            Text(
                text = texto,
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
    }
}

fun aleatorio(): String {
    //Android choice generator
    val list = listOf("Piedra", "Papel", "Tijeras")
    val randomIndex = Random.nextInt(list.size)
    return list[randomIndex]
}

@Composable
fun boton(bvlaue: String, onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .height(120.dp)
            .width(120.dp)
            .padding(10.dp),
        onClick = onClick,
        shape = RoundedCornerShape(14.dp)
    ) {
        Text(text = bvlaue)
    }
}

@Composable
fun Playeraction(jugador: String, maquina: String) {
    Text(
        text = jugador,
        fontSize = 16.sp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
    Text(
        text = maquina,
        fontSize = 32.sp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally),
        fontWeight = FontWeight.Bold
    )
}

fun puntuacion(jugador: String, maquina: String): Int {
    var victoria = 0
    if (jugador == maquina)

        victoria = 2
    else if (jugador == "Piedra" && maquina == "Papel")
        victoria = 0
    else if (jugador == "Piedra" && maquina == "Tijeras")
        victoria = 1
    else if (jugador == "Papel" && maquina == "Piedra")
        victoria = 1
    else if (jugador == "Papel" && maquina == "Tijeras")
        victoria = 0
    else if (jugador == "Tijeras" && maquina == "Papel")
        victoria = 1
    else if (jugador == "Tijeras" && maquina == "Piedra")
        victoria = 0
    return victoria
}







