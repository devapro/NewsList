package pro.devapp.newslist.ui.screens.newslist.customviews

import androidx.recyclerview.widget.DiffUtil
import pro.devapp.newslist.logic.models.ModelItemNews

class NewsDiffUtil: DiffUtil.ItemCallback<ModelItemNews>() {
    override fun areItemsTheSame(oldItem: ModelItemNews, newItem: ModelItemNews): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ModelItemNews, newItem: ModelItemNews): Boolean {
        return oldItem == newItem
    }
}