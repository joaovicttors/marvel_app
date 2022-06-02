package com.joaovicttors.data.models

import com.joaovicttors.bases.data.BaseModel

data class CharacterResponse(
    val id: Int?,
    val name: String?,
    val thumbnail: Thumbnail,
    val description: String?,
    val resourceUri: String?,
) : BaseModel() {

    data class Thumbnail(
        val path: String?,
        val extension: String?,
    ) : BaseModel()
}