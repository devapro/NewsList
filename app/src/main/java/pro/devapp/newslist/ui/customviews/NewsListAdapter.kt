package pro.devapp.newslist.ui.customviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.bumptech.glide.Glide
import pro.devapp.newslist.R
import pro.devapp.newslist.logic.entity.EntityNews
import java.text.SimpleDateFormat
import java.util.*

class NewsListAdapter(private val listener: ActionListener) : PagedListAdapter<EntityNews, NewsViewHolder>(POST_COMPARATOR) {
    private val formatter: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy hh:mm")
    private var errorMessage: String? = null

    companion object {
        val POST_COMPARATOR = NewsDiffUtil()
    }

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType){
            0 -> NewsViewHolder(inflater.inflate(NewsViewHolder.LAYOUT_ID, parent, false))
            1 -> {
                val holder = NewsFooterViewHolder(inflater.inflate(NewsFooterViewHolder.LAYOUT_ID, parent, false))
                holder.tryAgainBtn.setOnClickListener { listener.onTryAgainClick() }
                holder
            }
            else -> NewsViewHolder(inflater.inflate(NewsViewHolder.LAYOUT_ID, parent, false))
        }
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

        if(holder is NewsFooterViewHolder){
            holder.setError(errorMessage)
        }
    }

    override fun onBindViewHolder(
        holder: NewsViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()){
            onBindViewHolder(holder, position)
        }
        else if(holder is NewsFooterViewHolder){
            holder.setError(errorMessage)
        }
    }

    override fun getItemId(position: Int): Long {
        return getItem(position)?.id?.toLong() ?: 0L
    }

    override fun getItemViewType(position: Int): Int {
        return if(position == itemCount -1 ) 1 else 0
    }

    fun getItemByPosition(position: Int): EntityNews? {
        return super.getItem(position)
    }

    fun setError(message: String?){
        errorMessage = message
        notifyItemChanged(itemCount - 1, Object())
    }

    interface ActionListener{
        fun onTryAgainClick()
    }
}