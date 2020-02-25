package pro.devapp.newslist.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pro.devapp.newslist.storage.database.dao.NewsDao
import pro.devapp.newslist.storage.database.entity.EntityDbNews

@Database(entities = [EntityDbNews::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun newsDao(): NewsDao
}