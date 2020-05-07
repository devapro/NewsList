package pro.devapp.newslist.storage.livedata

import androidx.paging.ItemKeyedDataSource
import androidx.room.InvalidationTracker
import pro.devapp.newslist.logic.mapToModel
import pro.devapp.newslist.logic.models.ModelItemNews
import pro.devapp.newslist.storage.database.DataRepository
import pro.devapp.newslist.storage.database.entity.EntityDbNews

class NewsDataSource  (private val dataRepository: DataRepository) : ItemKeyedDataSource<Long, ModelItemNews>(){
    private val tables = arrayOf("news")

    private val observer = object : InvalidationTracker.Observer(tables) {
        override fun onInvalidated(tables: MutableSet<String>) {
            this@NewsDataSource.invalidate()
        }
    }

    init {
        dataRepository.addWeakObserver(this.observer)
    }

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<ModelItemNews>
    ) {
        val sinceDate = params.requestedInitialKey ?: 0L
        if(sinceDate == 0L){
            val result = dataRepository.getNewsInitial(params.requestedLoadSize)
            callback.onResult(result.mapToModel())
        } else {
            val resultInitial = dataRepository.getNewsInitial(sinceDate, params.requestedLoadSize)
            val resultAfter = dataRepository.getNewsAfterDate(sinceDate, params.requestedLoadSize)
            val result = ArrayList<EntityDbNews>()
            result.addAll(resultInitial)
            result.addAll(resultAfter)
            callback.onResult(result.mapToModel())
        }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<ModelItemNews>) {
        val sinceDate = params.key ?: 0
        val result = dataRepository.getNewsAfterDate(sinceDate, params.requestedLoadSize)
        callback.onResult(result.mapToModel())
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<ModelItemNews>) {
        val sinceDate = params.key ?: 0
        val result = dataRepository.getNewsBeforeDate(sinceDate, params.requestedLoadSize)
        callback.onResult(result.mapToModel())
    }

    override fun getKey(item: ModelItemNews): Long {
        return item.createdAt
    }


}