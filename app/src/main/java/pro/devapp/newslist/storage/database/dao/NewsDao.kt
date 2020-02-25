package pro.devapp.newslist.storage.database.dao

import androidx.room.Dao
import androidx.room.Query
import pro.devapp.newslist.storage.database.entity.EntityDbNews

@Dao
interface NewsDao : BaseDao<EntityDbNews>{
    @Query("SELECT * from news WHERE _id = :id LIMIT 1")
    fun findNewsById(id: Long): EntityDbNews?
    @Query("SELECT * from news WHERE url = :url LIMIT 1")
    fun findNewsByUrl(url: String): EntityDbNews?

    @Query("SELECT * FROM news ORDER BY createdAt DESC LIMIT :limit")
    fun getNewsInitial(limit: Int): List<EntityDbNews>

    @Query("SELECT * FROM news WHERE createdAt > :sinceDate ORDER BY createdAt DESC LIMIT :limit")
    fun getNewsInitial(sinceDate: Long, limit: Int): List<EntityDbNews>

    @Query("SELECT * FROM news WHERE createdAt > :sinceDate ORDER BY createdAt DESC LIMIT :limit")
    fun getNewsBeforeDate(sinceDate: Long, limit: Int): List<EntityDbNews>

    @Query("SELECT * FROM news WHERE createdAt < :sinceDate ORDER BY createdAt DESC LIMIT :limit")
    fun getNewsAfterDate(sinceDate: Long, limit: Int): List<EntityDbNews>

    @Query("SELECT COUNT(*) FROM news")
    fun getNewsTotalCount(): Int
}