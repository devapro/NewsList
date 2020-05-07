package pro.devapp.newslist.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import pro.devapp.newslist.R
import pro.devapp.newslist.logic.controllers.NavigationController

class MainActivity : AppCompatActivity() {

    private val presenter : NavigationController by inject{ parametersOf(supportFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.openInitFragment()
    }
}
