package pro.devapp.newslist.storage.database

import pro.devapp.newslist.storage.database.entity.EntityDbNews
import pro.devapp.newslist.storage.serverapi.entity.EntityApiNews

class ApiToDbEntityMapper {
    fun map(entity: EntityApiNews): EntityDbNews{
        return EntityDbNews(
            0,
            entity.title,
            entity.description,
            entity.content,
            entity.urlToImage,
            entity.url,
            entity.publishedAt.time
        )
    }

    fun map(id: Long, entity: EntityApiNews): EntityDbNews{
        return EntityDbNews(
            id,
            entity.title,
            entity.description,
            entity.content,
            entity.urlToImage,
            entity.url,
            entity.publishedAt.time
        )
    }
}