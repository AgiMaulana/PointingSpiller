package api

import api.response.PointingPokerResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface PointingPokerApi {
    @GET
    suspend fun getSessions(@Url url: String): PointingPokerResponse
}