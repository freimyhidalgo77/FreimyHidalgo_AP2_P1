package edu.ucne.freimyhidalgo_p1_ap2.presentation.tareas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun TareaScreen(
    viewModel: TareaViewModel = hiltViewModel(),
    goBack:()->Unit

){
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    TareaBodyScreen(
        uiState = uiState.value,
        onChangeDescripcion = viewModel::onChangeDescripcion,
        onChangeTiempo = viewModel::onChangeTiempo,
        save = viewModel::saveTarea,
        nuevo = viewModel::nuevaTarea,
        goBack = goBack

    )
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun TareaBodyScreen(
    uiState: UiState,
    onChangeDescripcion:(String)->Unit,
    onChangeTiempo: (Int) -> Unit,
    save:()->Unit,
    nuevo:()->Unit,
    goBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Crear tareas",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.White

                        )
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Blue
                )
            )

        }

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(16.dp)

        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Descripcion") },
                value = uiState.descripcion,
                onValueChange = onChangeDescripcion

            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Tiempo") },
                value = uiState.tiempo.toString(),
                onValueChange = {
                    val tiempo = it.toIntOrNull() ?: 0
                    onChangeTiempo(tiempo)
                }
            )


            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                OutlinedButton(
                    onClick = { save() },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp)

                ) {
                    Icon(Icons.Default.Edit, contentDescription = "Guardar")
                    Text("Guardar")

                }

                OutlinedButton(
                    onClick = { nuevo() },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp)

                ) {
                    Icon(Icons.Default.Refresh, contentDescription = "Nuevo")
                    Text("Nuevo")

                }

            }

            Spacer(modifier = Modifier.height(16.dp))
            uiState.successMessage?.let { menssage ->
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    content = {
                        Text(
                            text = menssage,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Green
                        )
                    }
                )
            }

            uiState.errorMessage?.let { menssage ->
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    content = {
                        Text(
                            text = menssage,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Red
                        )
                    }

                )
            }
        }

    }

}






