package com.raaceinm.androidpracticals.dataparse

import retrofit2.Response
import retrofit2.http.GET

interface DogApiService {
    @GET("woof.json")
    suspend fun getRandomDog(): Response<DogApiResponse>
}