package com.tejas.tabselector

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity(),
    View.OnTouchListener {

    companion object {
        private const val REQUEST_LOGIN = 1
    }

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var adapter: MyFragmentPagerAdapter

    private var isLoggedIn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        setupViewPager()

        tabLayout.setupWithViewPager(viewPager)
        setupTabs()

    }

    private fun init() {
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        adapter = MyFragmentPagerAdapter(supportFragmentManager)
    }

    private fun setupViewPager() {
        adapter = MyFragmentPagerAdapter(supportFragmentManager)

        for (i in 0 until 5) {
            adapter.addFragment(ContentFragment.newInstance("Content $i"), i.toString())
        }

        viewPager.adapter = adapter
    }

    private fun setupTabs() {
        for (i in 0 until tabLayout.tabCount) {
            val tab = tabLayout.getTabAt(i)

            @Suppress("INACCESSIBLE_TYPE")
            (tab?.view as? View)?.setOnTouchListener(this)
        }
    }

    override fun onTouch(p0: View?, event: MotionEvent): Boolean {

        Log.d("onTouch", "MotionEvent: ${event.action}")

        if (!isLoggedIn) {

            val duration: Long = event.eventTime - event.downTime

            if (event.action == MotionEvent.ACTION_DOWN) {
                return true
            } else if (event.action == MotionEvent.ACTION_UP) {
                //click
                Log.d("Clicked", "Clicked")
                startActivityForResult(
                    Intent(this, LoginActivity::class.java),
                    REQUEST_LOGIN
                )
            }
            return false
        }
        return false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_LOGIN -> {
                if (resultCode == Activity.RESULT_OK) {
                    isLoggedIn = true
                }
            }
        }
    }
}