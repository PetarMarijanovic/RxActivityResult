package com.petarmarijanovic.rxactivityresult

import android.app.Activity
import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

/** Created by petar on 26/07/2017. */
class RxActivityResult(activity: Activity) {
  
  companion object {
    private val FRAGMENT_TAG = "com.petarmarijanovic.rxactivityresult.RxActivityResultFragment"
  }
  
  private val fragment: RxActivityResultFragment
  
  init {
    var attachedFragment = activity.findFragmentByTag(FRAGMENT_TAG) as RxActivityResultFragment?
    
    if (attachedFragment == null) {
      attachedFragment = RxActivityResultFragment()
      activity.commitFragment(attachedFragment, FRAGMENT_TAG)
    }
    
    this.fragment = attachedFragment
  }
  
  fun observe(intent: Intent, requestCode: Int) = fragment.observe(intent, requestCode)
}

class RxActivityResultFragment : Fragment() {
  
  private val resultSubject = PublishSubject.create<ActivityResult>()
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    retainInstance = true
  }
  
  fun observe(intent: Intent, requestCode: Int): Single<Int> {
    startActivityForResult(intent, requestCode)
    return resultSubject.filter { it.requestCode == requestCode }.map { it.resultCode }.firstOrError()
  }
  
  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    resultSubject.onNext(ActivityResult(requestCode, resultCode, data))
  }
  
  private data class ActivityResult(val requestCode: Int, val resultCode: Int, val data: Intent?)
  
}
