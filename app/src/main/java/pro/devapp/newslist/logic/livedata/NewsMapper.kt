package pro.devapp.newslist.logic.livedata

import pro.devapp.newslist.logic.entity.EntityNews
import pro.devapp.newslist.storage.database.entity.EntityDbNews

class NewsMapper {
    fun map(entityDb: EntityDbNews): EntityNews{
        return EntityNews(
            entityDb.id,
            entityDb.title,
            entityDb.description,
            entityDb.content,
            entityDb.imageUrl,
            entityDb.url,
            entityDb.createdAt
        )
    }

    fun map(images: List<EntityDbNews>): List<EntityNews> {
        val result = ArrayList<EntityNews>()
        for (image in images) {
            result.add(map(image))
        }
        return result
    }
}