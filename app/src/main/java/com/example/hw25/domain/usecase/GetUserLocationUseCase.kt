package com.example.hw25.domain.usecase

import android.location.Location
import com.example.hw25.domain.repository.UserLocationRepository
import javax.inject.Inject

class GetUserLocationUseCase @Inject constructor(private val userLocationRepository: UserLocationRepository) {
    suspend operator fun invoke(): Location? {
        return userLocationRepository.getUserLocation()
    }
}