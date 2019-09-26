package com.petarmarijanovic.rxactivityresult.sample


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.patloew.rxlocation.RxLocation
import com.petarmarijanovic.rxactivityresult.RxActivityResult


class MainViewModelFactory(mainActivity: MainActivity) : ViewModelProvider.Factory {
    //normally all properties should be injected, e.g. with @Inject and Dagger2
    val rxActivityResult = RxActivityResult(mainActivity)
    val rxLocation = RxLocation(mainActivity)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(rxActivityResult, rxLocation) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class $modelClass")
    }
}
