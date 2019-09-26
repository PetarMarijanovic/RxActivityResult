package com.petarmarijanovic.rxactivityresult;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import io.reactivex.Single;

/** Created by petar on 26/07/2017. */
public class RxActivityResult {

  private static final String FRAGMENT_TAG =
      "com.petarmarijanovic.rxactivityresult.RxActivityResultFragment";

  private RxActivityResultFragment fragment;

  /** TODO: Write JavaDoc. */
  public RxActivityResult(@NonNull FragmentActivity activity) {
    FragmentManager manager = activity.getSupportFragmentManager();

    RxActivityResultFragment attachedFragment =
        (RxActivityResultFragment) manager.findFragmentByTag(FRAGMENT_TAG);

    if (attachedFragment == null) {
      attachedFragment = new RxActivityResultFragment();
      manager.beginTransaction().add(attachedFragment, FRAGMENT_TAG).commitAllowingStateLoss();
      manager.executePendingTransactions();
    }

    this.fragment = attachedFragment;
  }

  /** TODO: Write JavaDoc. */
  public Single<ActivityResult> start(final Intent intent) {
    return fragment.start(intent);
  }

  /** TODO: Write JavaDoc. */
  public Single<ActivityResult> start(final PendingIntent pendingIntent) {
    return fragment.start(pendingIntent);
  }

  /**
   * Create an intent for a specific component.  All other fields (action, data,
   * type, class) are null, though they can be modified later with explicit
   * calls.  This provides a convenient way to create an intent that is
   * intended to execute a hard-coded class name, rather than relying on the
   * system to find an appropriate class for you; see {@link Intent#setComponent}
   * for more information on the repercussions of this.
   *
   * NOTE: Application context is used as package context.
   *
   * @param cls The component class that is to be used for the intent.
   *
   * @see Intent#setClass
   * @see Intent#setComponent
   * @see Intent#Intent(android.content.Context, java.lang.Class)
   */
  public Intent newExplicitIntent(Class<?> cls) {
    Context applicationContext = fragment.getContext().getApplicationContext();
    return new Intent(applicationContext, cls);
  }
}
