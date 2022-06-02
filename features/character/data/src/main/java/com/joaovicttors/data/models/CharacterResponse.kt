package com.joaovicttors.data.models

import com.google.gson.annotations.SerializedName
import com.joaovicttors.bases.data.BaseModel

data class CharacterResponse(
    @SerializedName(CODE) val code: Int?,
    @SerializedName(STATUS) val status: String?,
    @SerializedName(COPYRIGHT) val copyright: String?,
    @SerializedName(DATA) val data: Data,
) : BaseModel() {

    data class Data(
        @SerializedName(OFFSET) val offset: Int?,
        @SerializedName(LIMIT) val limit: Int?,
        @SerializedName(TOTAL) val total: Int,
        @SerializedName(COUNT) val count: Int?,
        @SerializedName(RESULTS) val results: List<Character>,
    ) : BaseModel() {

        private companion object {
            private const val OFFSET: String = "offset"
            private const val LIMIT: String = "limit"
            private const val TOTAL: String = "total"
            private const val COUNT: String = "count"
            private const val RESULTS: String = "results"
        }
    }

    data class Character(
        @SerializedName(ID) val id: Int?,
        @SerializedName(NAME) val name: String?,
        @SerializedName(THUMBNAIL) val thumbnail: Thumbnail?,
        @SerializedName(DESCRIPTION) val description: String?,
        @SerializedName(RESOURCE_URI) val resourceUri: String?,
    ) : BaseModel() {

        private companion object {
            private const val ID: String = "id"
            private const val NAME: String = "name"
            private const val THUMBNAIL: String = "thumbnail"
            private const val DESCRIPTION: String = "description"
            private const val RESOURCE_URI: String = "resourceURI"
        }
    }

    data class Thumbnail(
        @SerializedName(PATH) val path: String?,
        @SerializedName(EXTENSION) val extension: String?,
    ) : BaseModel() {

        private companion object {
            private const val PATH: String = "path"
            private const val EXTENSION: String = "extension"
        }
    }

    private companion object {
        private const val CODE: String = "code"
        private const val STATUS: String = "status"
        private const val COPYRIGHT: String = "copyright"
        private const val DATA: String = "data"
    }
}