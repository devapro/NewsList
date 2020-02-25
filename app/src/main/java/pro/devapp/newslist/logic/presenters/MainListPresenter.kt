package pro.devapp.newslist.logic.presenters

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import pro.devapp.newslist.logic.DataController
import pro.devapp.newslist.logic.entity.EntityNews
import pro.devapp.newslist.logic.livedata.NewsBoundaryCallback
import pro.devapp.newslist.logic.livedata.NewsDataSourceFactory
import pro.devapp.newslist.storage.database.DataRepository

class MainListPresenter(dataRepository: DataRepository, private val dataController: DataController) {

    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(20)
        .setPageSize(10)
        .setPrefetchDistance(5)
        .build()

    private val factory =
       NewsDataSourceFactory(dataRepository)

    val pagedList : LiveData<PagedList<EntityNews>> = LivePagedListBuilder<Long, EntityNews>(factory, config)
        .setBoundaryCallback(NewsBoundaryCallback(dataController))
        .setInitialLoadKey(null)
        .build()

    fun getErrorMessage(): LiveData<String>{
        return dataController.errorMessage
    }

    fun tryAgain(){
        dataController.loadSinceData(0)
    }
}