package com.petarmarijanovic.rxactivityresult;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.annotation.NonNull;

import java.util.Random;

import io.reactivex.Single;

/** Created by petar on 26/07/2017. */
public class RxActivityResult {

  private static final String FRAGMENT_TAG =
      "com.petarmarijanovic.rxactivityresult.RxActivityResultFragment";

  private RxActivityResultFragment fragment;
  private Random random = new Random();

  public RxActivityResult(@NonNull Activity activity) {
    FragmentManager manager = activity.getFragmentManager();

    RxActivityResultFragment attachedFragment =
        (RxActivityResultFragment) manager.findFragmentByTag(FRAGMENT_TAG);

    if (attachedFragment == null) {
      attachedFragment = new RxActivityResultFragment();
      manager.beginTransaction().add(attachedFragment, FRAGMENT_TAG).commitAllowingStateLoss();
      manager.executePendingTransactions();
    }

    this.fragment = attachedFragment;
  }

  public Single<ActivityResult> start(Intent intent) {
    return fragment.start(intent, random.nextInt());
  }
}
