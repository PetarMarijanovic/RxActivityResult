package com.petarmarijanovic.rxactivityresult;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.petarmarijanovic.rxactivityresult.RxActivityResultFragment.ActivityResult;

import io.reactivex.Single;

/** Created by petar on 26/07/2017. */
public class RxActivityResult {

  private static final String FRAGMENT_TAG =
      "com.petarmarijanovic.rxactivityresult.RxActivityResultFragment";

  private RxActivityResultFragment fragment;

  public RxActivityResult(@NonNull Activity activity) {

    FragmentManager fragmentManager = activity.getFragmentManager();
    RxActivityResultFragment attachedFragment =
        (RxActivityResultFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG);

    if (attachedFragment == null) {
      attachedFragment = new RxActivityResultFragment();
      fragmentManager
          .beginTransaction()
          .add(attachedFragment, FRAGMENT_TAG)
          .commitAllowingStateLoss();
      fragmentManager.executePendingTransactions();
    }

    this.fragment = attachedFragment;
  }

  public Single<ActivityResult> single(Intent intent, int requestCode) {
    return fragment.single(intent, requestCode);
  }
}
