package pro.devapp.newslist.storage.livedata

import androidx.paging.DataSource
import pro.devapp.newslist.logic.models.ModelItemNews
import pro.devapp.newslist.storage.database.DataRepository

class NewsDataSourceFactory (private val dataRepository: DataRepository) : DataSource.Factory<Long, ModelItemNews>() {

    override fun create(): DataSource<Long, ModelItemNews> {
        return NewsDataSource(
            dataRepository
        )
    }

}