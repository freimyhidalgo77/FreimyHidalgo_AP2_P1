package edu.ucne.freimyhidalgo_p1_ap2.presentation.tareas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.freimyhidalgo_p1_ap2.data.local.entity.TareaEntity
import edu.ucne.freimyhidalgo_p1_ap2.repository.TareaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TareaViewModel @Inject constructor(

    private val tareaRepository: TareaRepository

): ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
      val uiState get() = _uiState.asStateFlow()


    init{
        getTarea()

    }

    fun getTarea(){
        viewModelScope.launch {
            tareaRepository.tareaDao.getAll().collect{tarea->
                _uiState.update {
                    it.copy(tareas = tarea)
                }
            }
        }
    }

    fun saveTarea(){
        viewModelScope.launch {
            if(_uiState.value.descripcion.isBlank() || _uiState.value.tiempo == null){
                _uiState.update {
                    it.copy(
                        errorMessage = "Todos los campos deben ser rellenados!", successMessage = null
                    )
                }
                return@launch
            }
            try{
                tareaRepository.saveTarea(_uiState.value.toEntity())
                _uiState.update {
                    it.copy(
                        successMessage = "La tarea se ha guardado con exito!!", errorMessage = null
                    )
                }

                nuevaTarea()
            }catch(e:Exception){
                _uiState.update {
                    it.copy(
                        errorMessage = "Hubo un error al guardar la tarea", successMessage = null
                    )
                }
            }
        }

    }

    fun deleteTarea(){
        viewModelScope.launch {
            try{
                tareaRepository.deleteTarea(_uiState.value.toEntity())
                _uiState.update {
                    it.copy(
                        successMessage = "La tarea se ha eliminado con exito!!", errorMessage = null
                    )
                }

            }catch(e:Exception){
                _uiState.update {
                    it.copy(
                        errorMessage = "Hubo un error al eliminar la tarea "
                    )
                }
            }
        }

    }

    fun nuevaTarea(){
        _uiState.update {
            it.copy(
                tareaId = null,
                descripcion = "",
                tiempo = null

            )
        }

    }

    fun selectTarea(tareaId:Int){
        viewModelScope.launch {
            val tarea = tareaRepository.findTarea(tareaId)
            if(tareaId > 0){
                _uiState.update {
                    it.copy(
                        tareaId = tarea?.tareaId,
                        descripcion = tarea?.descripcion?:"",
                        tiempo = tarea?.tiempo
                    )
                }
            }
        }
    }

    fun nuevoMensaje(){
        _uiState.update {
            it.copy(
                successMessage = null, errorMessage = null
            )
        }
    }

    fun onChangeDescripcion(descripcion:String){
        _uiState.update {
            it.copy(
                descripcion = descripcion
            )
        }
    }

    fun onChangeTiempo(tiempo:Int){
        _uiState.update {
            it.copy(
                tiempo = tiempo
            )
        }

    }


    fun UiState.toEntity() = TareaEntity(
        tareaId = tareaId,
        descripcion = descripcion,
        tiempo = tiempo

    )

}