package com.joaovicttors.data.datasources.remote.service

import com.joaovicttors.data.models.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterRemoteService {

    @GET(CHARACTERS_END_POINT)
    suspend fun getCharacterList(@Query("offset") offset: Int): CharacterResponse

    private companion object {

        private const val CHARACTERS_END_POINT: String = "characters"
    }
}