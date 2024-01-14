package com.sanjay.random_memes_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var currentImageUrl: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadmeme()
    }
 private fun loadmeme() {
     // Instantiate the RequestQueue.
     val progressBar = findViewById<View>(R.id.progressBar)
     progressBar.visibility = View.VISIBLE
     val nextButton = findViewById<View>(R.id.nextButton)
     nextButton.isEnabled = false
     val shareButton = findViewById<View>(R.id.shareButton)
     shareButton.isEnabled = false

     val memescall = MemeApiService.memeinstance.getMemes("url")
     memescall.enqueue(object : retrofit2.Callback<Meme> {
         override fun onResponse(call: Call<Meme>?, response: Response<Meme>?) {
             Log.d("Here is your meme", "Response Code: ${response?.code()}")
             if (response != null && response.isSuccessful) {
                 val memes = response.body()
                 Log.d("Memes app", memes.toString())
             } else {
                 Log.d("Memes", "Error in onResponse: ${response?.errorBody()?.string()}")
             }
         }

         override fun onFailure(call: Call<Meme>?, t: Throwable?) {
             Log.d("Memes app", "Error in Fetching News", t)

         }
     })
 }

      fun sharememe(view: View) {
          val intent = Intent(Intent.ACTION_SEND)
          intent.type = "text/plain"
          intent.putExtra(Intent.EXTRA_TEXT, "Hey, checkout this cool meme I got from Reditt $currentImageUrl")
          val chooser = Intent.createChooser(intent, "Share this meme using ...")
          startActivity(chooser)
      }
    fun nextmeme(view: View) {
        loadmeme()
    }
}

