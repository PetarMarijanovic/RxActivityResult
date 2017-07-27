package com.petarmarijanovic.rxactivityresult;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;

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
        .firstOrError();
  }

  @NonNull
  private Predicate<Pair<Integer, ActivityResult>> isRequestCodeEqual(final int requestCode) {
    return new Predicate<Pair<Integer, ActivityResult>>() {
      @Override
      public boolean test(@NonNull Pair<Integer, ActivityResult> result) throws Exception {
        return result.first == requestCode;
      }
    };
  }

  @NonNull
  private Function<Pair<Integer, ActivityResult>, ActivityResult> toActivityResult() {
    return new Function<Pair<Integer, ActivityResult>, ActivityResult>() {
      @Override
      public ActivityResult apply(@NonNull Pair<Integer, ActivityResult> result) throws Exception {
        return result.second;
      }
    };
  }
}
