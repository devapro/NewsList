package pro.devapp.newslist.storage.database

import androidx.room.InvalidationTracker
import pro.devapp.newslist.storage.database.entity.EntityDbNews

interface DataRepository {
    fun saveNews(items: List<EntityDbNews>)
    fun saveNews(item: EntityDbNews)
    fun findNewsById(id: Long): EntityDbNews?
    fun findNewsByUrl(url: String): EntityDbNews?
    fun getNewsInitial(limit: Int): List<EntityDbNews>
    fun getNewsInitial(sinceDate: Long, limit: Int): List<EntityDbNews>
    fun getNewsBeforeDate(sinceDate: Long, limit: Int): List<EntityDbNews>
    fun getNewsAfterDate(sinceDate: Long, limit: Int): List<EntityDbNews>
    fun getNewsTotalCount(): Int
    fun addWeakObserver(observer: InvalidationTracker.Observer)
}