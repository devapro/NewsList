package pro.devapp.newslist.logic.entity

class EntityNews (
    val id: Long,
    val title: String?,
    val description: String?,
    val content: String?,
    val imageUrl: String?,
    val url: String,
    val createdAt: Long
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EntityNews

        if (id != other.id) return false
        if (title != other.title) return false
        if (description != other.description) return false
        if (content != other.content) return false
        if (imageUrl != other.imageUrl) return false
        if (url != other.url) return false
        if (createdAt != other.createdAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (content?.hashCode() ?: 0)
        result = 31 * result + (imageUrl?.hashCode() ?: 0)
        result = 31 * result + url.hashCode()
        result = 31 * result + createdAt.hashCode()
        return result
    }
}