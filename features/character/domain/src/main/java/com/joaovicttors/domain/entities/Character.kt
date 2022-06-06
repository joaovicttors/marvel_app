package com.joaovicttors.domain.entities

import com.joaovicttors.bases.domain.BaseEntity

data class Character(
    val id: Int?,
    val name: String?,
    val thumbnail: String?,
    val description: String?,
    val resourceUri: String?,
) : BaseEntity()