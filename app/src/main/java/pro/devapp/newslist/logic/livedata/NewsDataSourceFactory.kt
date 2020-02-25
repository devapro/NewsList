package pro.devapp.newslist.logic.livedata

import androidx.paging.DataSource
import pro.devapp.newslist.logic.entity.EntityNews
import pro.devapp.newslist.storage.database.DataRepository

class NewsDataSourceFactory (private val dataRepository: DataRepository) : DataSource.Factory<Long, EntityNews>() {

    override fun create(): DataSource<Long, EntityNews> {
        return NewsDataSource(
            dataRepository
        )
    }

}