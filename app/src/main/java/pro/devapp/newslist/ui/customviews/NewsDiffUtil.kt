package pro.devapp.newslist.ui.customviews

import androidx.recyclerview.widget.DiffUtil
import pro.devapp.newslist.logic.entity.EntityNews

class NewsDiffUtil: DiffUtil.ItemCallback<EntityNews>() {
    override fun areItemsTheSame(oldItem: EntityNews, newItem: EntityNews): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EntityNews, newItem: EntityNews): Boolean {
        return oldItem == newItem
    }
}