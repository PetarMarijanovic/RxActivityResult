package com.petarmarijanovic.rxactivityresult.sample

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.petarmarijanovic.rxactivityresult.R

class Main2Activity : AppCompatActivity() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main2)
    
    findViewById(R.id.ok).setOnClickListener { finish(Activity.RESULT_OK) }
    findViewById(R.id.cancel).setOnClickListener { finish(Activity.RESULT_CANCELED) }
  }
  
  private fun finish(result: Int) {
    setResult(result)
    finish()
  }
}
