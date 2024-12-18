package com.example.flowtogether.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CustomButtons(
    onAddTaskClick: () -> Unit,
    onBackClick: () -> Unit
) {
    // Centrado en pantalla con un espaciado vertical
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp), // Padding vertical adicional
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), // Espaciado lateral
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Botón "Agregar Tarea" con ícono "+"
            CustomButton(
                iconRes = android.R.drawable.ic_input_add, // Ícono de "+"
                backgroundColor = Color(0xFF123524), // Verde
                size = 80.dp,
                onClick = onAddTaskClick
            )

            // Botón "Volver" con ícono de flecha
            CustomButton(
                iconRes = android.R.drawable.ic_media_previous, // Flecha hacia atrás
                backgroundColor = Color(0xFF123524), // Color personalizado #123524
                size = 80.dp,
                onClick = onBackClick
            )

        }
    }
}

@Composable
fun CustomButton(
    iconRes: Int,
    backgroundColor: Color,
    size: Dp,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(16.dp), // Bordes redondeados
        modifier = Modifier.size(size)
    ) {
        // Ícono centrado
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(size * 0.5f) // Ícono ocupa 50% del botón
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomButtons() {
    CustomButtons(
        onAddTaskClick = {},
        onBackClick = {}
    )
}
