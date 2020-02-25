package pro.devapp.newslist.ui.fragments

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.view_toolbar.*
import pro.devapp.newslist.ui.MainActivity

open class NavigationFragment : Fragment(){

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                (activity as AppCompatActivity).supportFragmentManager.popBackStack()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    protected fun displayHome(){
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    protected fun setTitle(title: String){
        (activity as MainActivity).supportActionBar?.title = title
    }
}