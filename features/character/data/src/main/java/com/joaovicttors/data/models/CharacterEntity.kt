package com.joaovicttors.data.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.joaovicttors.bases.data.BaseModel

@Entity(tableName = "character")
data class CharacterEntity(
    @ColumnInfo(name = ID) @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = CHARACTER_ID) val characterId: Int?,
    @ColumnInfo(name = NAME) val name: String?,
    @ColumnInfo(name = THUMBNAIL) val thumbnail: String?,
    @ColumnInfo(name = DESCRIPTION) val description: String?,
    @ColumnInfo(name = RESOURCE_URI) val resourceUri: String?,
) : BaseModel() {

    private companion object {
        private const val ID: String = "id"
        private const val CHARACTER_ID: String = "characterId"
        private const val NAME: String = "name"
        private const val THUMBNAIL: String = "thumbnail"
        private const val DESCRIPTION: String = "description"
        private const val RESOURCE_URI: String = "resourceURI"
    }
}