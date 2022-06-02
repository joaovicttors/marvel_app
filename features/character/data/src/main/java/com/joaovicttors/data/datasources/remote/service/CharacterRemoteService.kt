package com.joaovicttors.data.datasources.remote.service

import com.joaovicttors.data.models.CharacterResponse
import retrofit2.http.GET

interface CharacterRemoteService {

    @GET(CHARACTERS_END_POINT)
    suspend fun getCharacterList(): CharacterResponse

    private companion object {

        private const val CHARACTERS_END_POINT: String = "characters?apikey=161efc45f87a82340ae16aa8a72fabab"
    }
}