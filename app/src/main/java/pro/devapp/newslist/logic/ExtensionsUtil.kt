package pro.devapp.newslist.logic

import pro.devapp.newslist.logic.models.ModelItemNews
import pro.devapp.newslist.storage.database.entity.EntityDbNews

fun EntityDbNews.mapToModel(): ModelItemNews {
    return ModelItemNews(
        this.id,
        this.title,
        this.description,
        this.content,
        this.imageUrl,
        this.url,
        this.createdAt
    )
}

fun List<EntityDbNews>.mapToModel(): List<ModelItemNews>{
    val result = ArrayList<ModelItemNews>()
    for (item in this) {
        result.add(item.mapToModel())
    }
    return result
}