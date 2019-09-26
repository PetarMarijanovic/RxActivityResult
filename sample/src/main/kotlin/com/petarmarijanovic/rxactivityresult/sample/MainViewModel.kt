package com.petarmarijanovic.rxactivityresult.sample

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationSettingsRequest
import com.patloew.rxlocation.RxLocation
import com.patloew.rxlocation.StatusException
import com.petarmarijanovic.rxactivityresult.ActivityResult
import com.petarmarijanovic.rxactivityresult.RxActivityResult
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy

class MainViewModel(val rxActivityResult: RxActivityResult, val rxLocation: RxLocation) : ViewModel() {
    private val subscriptions = CompositeDisposable()

    val bluetoothStatus = MutableLiveData(ApiStatus.NOT_CHECKED);

    val otherActivityStatus = MutableLiveData(ApiStatus.NOT_CHECKED);

    val locationStatus = MutableLiveData(ApiStatus.NOT_CHECKED);

    fun enableBluetooth() {
        subscriptions += rxActivityResult.start(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE))
                .map { it.toApiStatus() }
                .subscribeBy(
                        onSuccess = bluetoothStatus::postValue,
                        onError = { it.printStackTrace() }
                )
    }

    fun startOtherActivity() {
        val startOtherActivityIntent = rxActivityResult.newExplicitIntent(OtherActivity::class.java)

        subscriptions += rxActivityResult.start(startOtherActivityIntent)
                .map { it.toApiStatus() }
                .subscribeBy(
                        onSuccess = otherActivityStatus::postValue,
                        onError = { it.printStackTrace() }
                )
    }

    fun enableLocation() {
        val locationSettingsRequest = LocationSettingsRequest.Builder()
                .addLocationRequest(LocationRequest.create().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY))
                .build()

        subscriptions += rxLocation.settings().check(locationSettingsRequest)
                .map { it.status }
                .map { ApiStatus.OK } //it's always ok unless there is error, which is handled below
                .onErrorResumeNext {
                    if (it is StatusException && it.status.hasResolution()) {
                        rxActivityResult.start(it.status.resolution)
                                .map { it.toApiStatus() }
                    } else {
                        Single.just(ApiStatus.CANCELED)
                    }
                }
                .subscribeBy(
                        onSuccess = locationStatus::postValue,
                        onError = { it.printStackTrace() }
                )
    }

    override fun onCleared() {
        subscriptions.dispose()
    }

}


fun ActivityResult.toApiStatus() = if (isOk) ApiStatus.OK else ApiStatus.CANCELED
