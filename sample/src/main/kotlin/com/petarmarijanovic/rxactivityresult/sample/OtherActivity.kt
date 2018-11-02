package com.petarmarijanovic.rxactivityresult.sample

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_other.*

class OtherActivity : AppCompatActivity() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_other)
    
    ok_button.setOnClickListener { finish(Activity.RESULT_OK) }
    cancel_button.setOnClickListener { finish(Activity.RESULT_CANCELED) }
  }
  
  private fun finish(result: Int) {
    setResult(result)
    finish()
  }
}
