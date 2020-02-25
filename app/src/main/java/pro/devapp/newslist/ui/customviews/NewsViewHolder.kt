package pro.devapp.newslist.ui.customviews

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import pro.devapp.newslist.R


class NewsViewHolder (itemView: View)  : RecyclerView.ViewHolder(itemView) {
    companion object{
        val LAYOUT_ID = R.layout.view_item_news
    }
    val container = itemView.findViewById<ConstraintLayout>(R.id.container)
    val image = itemView.findViewById<ImageView>(R.id.image)
    val title = itemView.findViewById<TextView>(R.id.title)
    val description = itemView.findViewById<TextView>(R.id.description)
    val date = itemView.findViewById<TextView>(R.id.date)
}