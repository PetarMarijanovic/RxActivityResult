package com.petarmarijanovic.rxactivityresult;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;

import rx.Single;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

/** Created by petar on 26/07/2017. */
public class RxActivityResultFragment extends Fragment {

  private PublishSubject<Pair<Integer, ActivityResult>> resultSubject = PublishSubject.create();

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    resultSubject.onNext(Pair.create(requestCode, new ActivityResult(resultCode, data)));
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);
  }

  /** TODO: Write JavaDoc. */
  public Single<ActivityResult> start(final Intent intent) {
    int requestCode = RequestCodeGenerator.generate();

    startActivityForResult(intent, requestCode);

    return resultSubject
        .filter(isRequestCodeEqual(requestCode))
        .map(toActivityResult())
        .first()
        .toSingle();
  }

  @NonNull
  private Func1<Pair<Integer, ActivityResult>, Boolean> isRequestCodeEqual(final int requestCode) {
    return new Func1<Pair<Integer, ActivityResult>, Boolean>() {
      @Override
      public Boolean call(Pair<Integer, ActivityResult> result) {
        return result.first == requestCode;
      }
    };
  }

  @NonNull
  private Func1<Pair<Integer, ActivityResult>, ActivityResult> toActivityResult() {
    return new Func1<Pair<Integer, ActivityResult>, ActivityResult>() {
      @Override
      public ActivityResult call(Pair<Integer, ActivityResult> result) {
        return result.second;
      }
    };
  }
}
