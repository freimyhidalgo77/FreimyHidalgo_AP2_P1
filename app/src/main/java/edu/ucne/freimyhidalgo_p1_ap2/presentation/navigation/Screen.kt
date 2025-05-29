package edu.ucne.freimyhidalgo_p1_ap2.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen{
    @Serializable
    data class Sistema(val id: Int?): Screen()

    @Serializable
    data object List: Screen()
}