package edu.ucne.freimyhidalgo_p1_ap2.presentation.tareas

import androidx.compose.runtime.Composable


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.text.TextStyle

@Composable
fun TareaDeleteScreen(
    viewModel: TareaViewModel = hiltViewModel(),
    tareaId:Int,
    goBack: () -> Unit
){

    LaunchedEffect(tareaId){
        viewModel.selectTarea(tareaId)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DeleteTareaBodyScreen (
        uiState = uiState,
        onDeleteSistema = {
            viewModel.deleteTarea()
            goBack()
        },
        goBack
    )

}


@Composable
fun DeleteTareaBodyScreen(
    uiState: UiState,
    onDeleteSistema:()-> Unit,
    goBack:()-> Unit

){

    Scaffold(
        topBar = {
            Text(
                text = "Estas seguro que deseas eliminar esta tarea?",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Red,
                    textAlign = TextAlign.Center
                ),

                )
        }
    )
    { innerPading ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPading)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)

            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {

                    Text(
                        text = "Descripcion: ${uiState.descripcion}",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(8.dp)
                    )

                    Text(
                        text = "Tiempo: ${uiState.tiempo}",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(8.dp)
                    )

                }

            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    onDeleteSistema()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)

            ){
                Text(text = "Eliminar")
            }

            Button(
                onClick = {
                    goBack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ){
                Text(text= "Cancelar")
            }

        }

    }

}



























