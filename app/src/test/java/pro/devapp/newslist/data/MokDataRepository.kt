package pro.devapp.newslist.data

import androidx.room.InvalidationTracker
import pro.devapp.newslist.storage.database.DataRepository
import pro.devapp.newslist.storage.database.entity.EntityDbNews

class MokDataRepository : DataRepository {
    override fun saveNews(items: List<EntityDbNews>) {
        TODO("Not yet implemented")
    }

    override fun saveNews(item: EntityDbNews) {
        TODO("Not yet implemented")
    }

    override fun findNewsById(id: Long): EntityDbNews? {
        TODO("Not yet implemented")
    }

    override fun findNewsByUrl(url: String): EntityDbNews? {
        TODO("Not yet implemented")
    }

    override fun getNewsInitial(limit: Int): List<EntityDbNews> {
        TODO("Not yet implemented")
    }

    override fun getNewsInitial(sinceDate: Long, limit: Int): List<EntityDbNews> {
        TODO("Not yet implemented")
    }

    override fun getNewsBeforeDate(sinceDate: Long, limit: Int): List<EntityDbNews> {
        TODO("Not yet implemented")
    }

    override fun getNewsAfterDate(sinceDate: Long, limit: Int): List<EntityDbNews> {
        TODO("Not yet implemented")
    }

    override fun getNewsTotalCount(): Int {
        TODO("Not yet implemented")
    }

    override fun addWeakObserver(observer: InvalidationTracker.Observer) {
        TODO("Not yet implemented")
    }
}