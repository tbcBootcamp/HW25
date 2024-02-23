package com.example.hw25.data.repository

import android.location.Geocoder
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.hw25.domain.repository.PlaceLocationRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class PlaceLocationRepositoryImpl @Inject constructor(
    private val geocoder: Geocoder
) : PlaceLocationRepository {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun getCoordinates(locationName: String): Flow<Result<LatLng>> = flow {
        try {
            val result = suspendCancellableCoroutine<Result<LatLng>> { continuation ->
                val lowerLeftLatitude = -90.0
                val lowerLeftLongitude = -180.0
                val upperRightLatitude = 90.0
                val upperRightLongitude = 180.0

                geocoder.getFromLocationName(
                    locationName,
                    1,
                    lowerLeftLatitude,
                    lowerLeftLongitude,
                    upperRightLatitude,
                    upperRightLongitude
                ) { addresses ->
                    if (addresses.isNotEmpty()) {
                        val location = addresses.first()
                        continuation.resume(
                            Result.success(
                                LatLng(
                                    location.latitude,
                                    location.longitude
                                )
                            )
                        )
                    } else {
                        continuation.resume(Result.failure(Exception("Location not found")))
                    }
                }
            }

            emit(result)
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}