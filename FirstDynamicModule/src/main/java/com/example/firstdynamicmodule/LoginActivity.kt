package com.example.firstdynamicmodule

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.play.core.splitcompat.SplitCompat


class LoginActivity : AppCompatActivity() {
    lateinit var button: Button
    lateinit var editText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dynamic_module_xml)
        button = findViewById(R.id.btnnext);
        editText = findViewById(R.id.edtname)

        val key = intent.getStringExtra("key")
        editText.setText(key)


        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (editText.text.toString().equals("123"))
                    launchnewActivty(v)
                else
                    Toast.makeText(this@LoginActivity, "wrong input", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun launchnewActivty(v: View?) {
        var intent = Intent(this, SecondActivity::class.java)

        startActivity(intent)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        SplitCompat.install(this)
    }
}