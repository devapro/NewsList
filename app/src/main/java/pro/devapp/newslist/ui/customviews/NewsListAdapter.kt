package pro.devapp.newslist.ui.customviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.bumptech.glide.Glide
import pro.devapp.newslist.R
import pro.devapp.newslist.logic.entity.EntityNews
import java.text.SimpleDateFormat

class NewsListAdapter : PagedListAdapter<EntityNews, NewsViewHolder>(POST_COMPARATOR) {
    var formatter: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy hh:mm")

    companion object {
        val POST_COMPARATOR = NewsDiffUtil()
    }

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NewsViewHolder(inflater.inflate(NewsViewHolder.LAYOUT_ID, parent, false))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = getItem(position)
        news?.title?.let {
            holder.title.text = it
        }
        news?.createdAt?.let {
            holder.date.text = formatter.format(it)
        }
        news?.description?.let {
            holder.description.text = it
        }

        news?.imageUrl?.let {
            Glide
                .with(holder.itemView.context)
                .load(it)
                .centerCrop()
                .into(holder.image);
        }
    }

    override fun getItemId(position: Int): Long {
        return getItem(position)?.id?.toLong() ?: 0L
    }

    fun getItemByPosition(position: Int): EntityNews? {
        return super.getItem(position)
    }
}