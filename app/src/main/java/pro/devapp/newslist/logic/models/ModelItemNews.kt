package pro.devapp.newslist.logic.models

data class ModelItemNews (
    val id: Long,
    val title: String?,
    val description: String?,
    val content: String?,
    val imageUrl: String?,
    val url: String,
    val createdAt: Long
)