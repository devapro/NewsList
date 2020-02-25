package pro.devapp.newslist.storage.serverapi

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import pro.devapp.newslist.application.SERVER_API_KEY
import pro.devapp.newslist.application.SERVER_SCHEME
import pro.devapp.newslist.application.SERVER_URL
import pro.devapp.newslist.storage.serverapi.entity.EntityApiNews
import java.lang.Exception

class ServerApi(private val client: OkHttpClient) : Api {

    private val gson = Gson()

    @Throws(Exception::class)
    override suspend fun loadNextPage(page: Int): List<EntityApiNews> {
        return withContext(Dispatchers.IO){
            val request = createRequest(page)
            val response = client.newCall(request).execute()
            if(response.isSuccessful && response.body != null){
                val data = gson.fromJson(response.body!!.string(), ApiResponse::class.java)
                if(data.status == "ok"){
                    return@withContext data.articles
                }
                else if (data.status == "error"){
                    throw Exception(data.message)
                }
            } else if(response.body != null) {
                val data = gson.fromJson(response.body!!.string(), ApiResponse::class.java)
                if (data.status == "error"){
                    throw Exception(data.message)
                }
            }
            throw Exception(response.message)
        }
    }

    private fun createRequest(page: Int): Request {
        val httpUrl = HttpUrl.Builder()
            .scheme(SERVER_SCHEME)
            .host(SERVER_URL)
            .addPathSegment("v2")
            .addPathSegment("everything")
            .addQueryParameter("q", "android")
            .addQueryParameter("from", "2019-04-00")
            .addQueryParameter("sortBy", "publishedAt")
            .addQueryParameter("apiKey", SERVER_API_KEY)
            .addQueryParameter("page", page.toString())
            .build()
        return Request.Builder()
            .url(httpUrl)
            .get()
            .build()
    }

    private data class ApiResponse(
        @SerializedName("status") val status: String,
        @SerializedName("message") val message: String,
        @SerializedName("articles") val articles: List<EntityApiNews>
    )
}