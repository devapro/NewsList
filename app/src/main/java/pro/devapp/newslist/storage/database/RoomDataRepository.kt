package pro.devapp.newslist.storage.database

import android.annotation.SuppressLint
import androidx.room.InvalidationTracker
import pro.devapp.newslist.storage.database.entity.EntityDbNews

class RoomDataRepository(private val dataBase: AppDataBase) : DataRepository {
    override fun saveNews(item: EntityDbNews) {
        dataBase.newsDao().insert(item)
    }

    override fun saveNews(items: List<EntityDbNews>) {
        dataBase.newsDao().insert(items)
    }

    override fun findNewsById(id: Long): EntityDbNews? {
        return dataBase.newsDao().findNewsById(id)
    }

    override fun findNewsByUrl(url: String): EntityDbNews? {
        return dataBase.newsDao().findNewsByUrl(url)
    }

    override fun getNewsInitial(limit: Int): List<EntityDbNews> {
        return dataBase.newsDao().getNewsInitial(limit)
    }

    override fun getNewsInitial(sinceDate: Long, limit: Int): List<EntityDbNews> {
        return dataBase.newsDao().getNewsInitial(limit)
    }

    override fun getNewsBeforeDate(sinceDate: Long, limit: Int): List<EntityDbNews> {
        return dataBase.newsDao().getNewsBeforeDate(sinceDate, limit)
    }

    override fun getNewsAfterDate(sinceDate: Long, limit: Int): List<EntityDbNews> {
        return dataBase.newsDao().getNewsAfterDate(sinceDate, limit)
    }

    override fun getNewsTotalCount(): Int {
        return dataBase.newsDao().getNewsTotalCount()
    }

    @SuppressLint("RestrictedApi")
    override fun addWeakObserver(observer: InvalidationTracker.Observer) {
        dataBase.invalidationTracker.addWeakObserver(observer)
    }
}