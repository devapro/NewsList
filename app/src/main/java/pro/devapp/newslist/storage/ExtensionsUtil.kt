package pro.devapp.newslist.storage

import pro.devapp.newslist.storage.database.entity.EntityDbNews
import pro.devapp.newslist.storage.serverapi.entity.EntityApiNews

fun EntityApiNews.mapToDbEntity(id: Long): EntityDbNews {
    return EntityDbNews(
        id,
        this.title,
        this.description,
        this.content,
        this.urlToImage,
        this.url,
        this.publishedAt.time
    )
}