package pro.devapp.newslist.storage.serverapi.entity

import com.google.gson.annotations.SerializedName
import java.util.*

data class EntityApiNews (
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("urlToImage") val urlToImage: String,
    @SerializedName("publishedAt") val publishedAt: Date,
    @SerializedName("content") val content: String,
    @SerializedName("url") val url: String
)