package com.tejas.tabselector

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()

        loginBtn.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    private fun init() {
        loginBtn = findViewById(R.id.loginBtn)
    }
}