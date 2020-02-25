package pro.devapp.newslist.storage.serverapi

import pro.devapp.newslist.storage.serverapi.entity.EntityApiNews

interface Api {
    suspend fun loadNextPage(page: Int): List<EntityApiNews>
}