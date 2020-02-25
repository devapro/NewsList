package pro.devapp.newslist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import pro.devapp.newslist.R
import pro.devapp.newslist.application.App
import pro.devapp.newslist.logic.presenters.MainListPresenter
import pro.devapp.newslist.ui.fragments.ListNewsFragment
import pro.devapp.newslist.ui.fragments.SplashFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openInitFragment()
    }

    private fun openInitFragment(){
        val currentFragment = supportFragmentManager.findFragmentByTag("main")
        if(currentFragment == null){
            addFragment(SplashFragment.newInstance())
            Handler().postDelayed({
                replaceFragment(ListNewsFragment.newInstance())
            }, 1000)
        }
    }

    fun replaceFragment(fragment: Fragment) {
        try {
            supportFragmentManager.beginTransaction().replace(R.id.content, fragment, "main").commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun addFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().addToBackStack("fr").add(R.id.content, fragment).commit()
    }
}
