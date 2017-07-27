package com.petarmarijanovic.rxactivityresult.sample

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.petarmarijanovic.rxactivityresult.ActivityResult
import com.petarmarijanovic.rxactivityresult.RxActivityResult
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  
  private lateinit var rxActivityResult: RxActivityResult
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    
    rxActivityResult = RxActivityResult(this)
    
    bluetooth_button.setOnClickListener { enableBluetooth() }
    activity_button.setOnClickListener { startActivity() }
  }
  
  private fun enableBluetooth() {
    rxActivityResult.start(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE))
        .subscribe({ updateTextView(bt_status, it) },
                   { it.printStackTrace() })
    
  }
  
  private fun startActivity() {
    rxActivityResult.start(Intent(this, OtherActivity::class.java))
        .subscribe({ updateTextView(activity_status, it) },
                   { it.printStackTrace() })
  }
  
  private fun updateTextView(view: TextView, result: ActivityResult) {
    if (result.isOk) {
      view.setText(R.string.result_ok)
      view.setTextColor(ContextCompat.getColor(this, R.color.green))
    } else if (result.isCanceled) {
      view.setText(R.string.result_canceled)
      view.setTextColor(ContextCompat.getColor(this, R.color.red))
    }
  }
}
