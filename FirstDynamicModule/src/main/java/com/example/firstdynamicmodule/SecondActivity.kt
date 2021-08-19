package com.example.firstdynamicmodule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firstexample.ClassNameConstant
import com.example.firstexample.TestActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val intent =Intent(this,TestActivity::class.java)
        intent.putExtra("abc","12345")
        startActivity(intent)

        println(ClassNameConstant.TEST_ACTIVITY)
    }
}