package com.example.hw25.presentation.event

sealed class MapFragmentEvent {
    data object GetUserCoordinates : MapFragmentEvent()
    data class GetPlaceCoordinatesByName(val locationName: String) : MapFragmentEvent()
}