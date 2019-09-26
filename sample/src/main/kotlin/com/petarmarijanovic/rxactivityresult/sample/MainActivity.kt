package com.petarmarijanovic.rxactivityresult.sample

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  //normally should be injected, e.g. with @Inject and Dagger2
  val viewModel: MainViewModel by viewModels { MainViewModelFactory(this) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    viewModel.bluetoothStatus.observe(this) {
      bt_status.updateStatus(it)
    }

    viewModel.otherActivityStatus.observe(this) {
      activity_status.updateStatus(it)
    }

    viewModel.locationStatus.observe(this) {
      location_status.updateStatus(it)
    }

    bluetooth_button.setOnClickListener { viewModel.enableBluetooth() }
    activity_button.setOnClickListener { viewModel.startOtherActivity() }
    location_button.setOnClickListener { viewModel.enableLocation() }

  }

  private fun TextView.updateStatus(result: ApiStatus) {
    this.setText(result.textId)
    this.setTextColor(ContextCompat.getColor(this.context, result.colorId))
  }
}

/*
 SAM conversion is not working with Kotlin until "New Type Inference Algorithm" is opt-ed explicitly
 (NOTE: it become default In future versions of Kotlin)
 Already now following function can be removed if SAM is opt-ed explicitly
 (by uncommenting '-XXLanguage:+NewInference' in build.gradle)
*/
inline fun <T> LiveData<T>.observe(owner: LifecycleOwner, crossinline kotlinObserver: (T) -> Unit) {
  this.observe(owner, Observer { kotlinObserver(it) })
}
