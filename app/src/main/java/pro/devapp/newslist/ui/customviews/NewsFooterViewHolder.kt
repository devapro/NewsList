package pro.devapp.newslist.ui.customviews

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import pro.devapp.newslist.R


class NewsFooterViewHolder (itemView: View): NewsViewHolder(itemView) {
    companion object{
        val LAYOUT_ID = R.layout.view_footer_item_news
    }
    private val error = itemView.findViewById<TextView>(R.id.error)
    val tryAgainBtn = itemView.findViewById<TextView>(R.id.tryAgain)

    fun setError(message: String?){
        if(message == null){
            error.visibility = GONE
            tryAgainBtn.visibility = GONE
        }
        else {
            error.visibility = VISIBLE
            tryAgainBtn.visibility = VISIBLE
            error.text = message
        }
    }
}