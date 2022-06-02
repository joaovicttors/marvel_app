package com.joaovicttors.data.models

import com.joaovicttors.bases.data.BaseModel

data class CharacterEntity(
    val id: Int?,
    val name: String?,
    val thumbnail: String?,
    val description: String?,
    val resourceUri: String?,
) : BaseModel()