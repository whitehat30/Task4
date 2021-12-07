package com.example.books

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button=findViewById<Button>(R.id.bookButton)
        val bookData=findViewById<TextView>(R.id.bookData)
        val author= findViewById<TextInputLayout>(R.id.author).editText?.text
        val country =findViewById<TextInputLayout>(R.id.country).editText?.text
        button.setOnClickListener()
        {
            var count=0
            val bookApplication=application as BookApplication
            val bookService=bookApplication.books

            CoroutineScope(Dispatchers.IO).launch {
                val decodedBook=bookService.getBooks()
                withContext(Dispatchers.Main)
                {
                    val myStringBuilder = StringBuilder()
                    for (myData in decodedBook) {
                        if(count<3) {
                            if (author.toString() == myData.author && country.toString() == myData.country) {
                                myStringBuilder.append("Result : "+myData.title)
                                myStringBuilder.append("\n")
                                count++
                            }
                        }
                    }
                    bookData.text = "Results : "+count+"\n$myStringBuilder"

                }
            }
        }
    }
}