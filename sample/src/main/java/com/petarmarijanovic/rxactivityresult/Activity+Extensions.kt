package com.petarmarijanovic.rxactivityresult

/** Created by petar on 26/07/2017. */
import android.app.Activity
import android.app.Fragment
import android.widget.Toast

/** Created by petar on 09/06/2017. */
fun Activity.toast(text: CharSequence) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

fun Activity.findFragmentByTag(tag: String): Fragment? = fragmentManager.findFragmentByTag(tag)

fun Activity.commitFragment(fragment: Fragment, tag: String) {
  fragmentManager.beginTransaction().add(fragment, tag).commitAllowingStateLoss()
  fragmentManager.executePendingTransactions()
}
