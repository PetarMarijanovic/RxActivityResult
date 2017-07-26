package com.petarmarijanovic.rxactivityresult

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    
    findViewById(R.id.enable).setOnClickListener { enable() }
  }
  
  private fun enable() {
    RxActivityResult(this).start(Intent(this, Main2Activity::class.java))
        .subscribe({ Log.d("Petarr", "success " + it) },
                   { Log.e("Petarr", "fail " + it.message) })
    
  }
}
