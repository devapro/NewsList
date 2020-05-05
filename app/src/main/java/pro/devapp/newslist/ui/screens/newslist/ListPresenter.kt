package pro.devapp.newslist.ui.screens.newslist

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import pro.devapp.newslist.logic.DataController
import pro.devapp.newslist.logic.models.ModelItemNews
import pro.devapp.newslist.storage.livedata.NewsBoundaryCallback
import pro.devapp.newslist.storage.livedata.NewsDataSourceFactory
import pro.devapp.newslist.storage.database.DataRepository

class ListPresenter(dataRepository: DataRepository, private val dataController: DataController) {

    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(20)
        .setPageSize(10)
        .setPrefetchDistance(5)
        .build()

    private val factory =
       NewsDataSourceFactory(dataRepository)

    val pagedList : LiveData<PagedList<ModelItemNews>> = LivePagedListBuilder<Long, ModelItemNews>(factory, config)
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