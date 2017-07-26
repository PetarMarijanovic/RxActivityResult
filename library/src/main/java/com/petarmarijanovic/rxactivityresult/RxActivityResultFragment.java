package com.petarmarijanovic.rxactivityresult;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;

/** Created by petar on 26/07/2017. */
// TODO support fragment?
public class RxActivityResultFragment extends Fragment {

  private PublishSubject<ActivityResult> resultSubject = PublishSubject.create();

  public Single<ActivityResult> single(Intent intent, final int requestCode) {
    startActivityForResult(intent, requestCode);
    return resultSubject
        .filter(
            new Predicate<ActivityResult>() {
              @Override
              public boolean test(@NonNull ActivityResult result) throws Exception {
                return result.getRequestCode() == requestCode;
              }
            })
        .firstOrError();
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    resultSubject.onNext(new ActivityResult(requestCode, resultCode, data));
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);
  }

  // TODO Separate class
  public static class ActivityResult {

    private int requestCode;
    private int resultCode;
    private Intent data;

    public ActivityResult(int requestCode, int resultCode, Intent data) {
      this.requestCode = requestCode;
      this.resultCode = resultCode;
      this.data = data;
    }

    public int getRequestCode() {
      return requestCode;
    }

    public int getResultCode() {
      return resultCode;
    }

    public Intent getData() {
      return data;
    }
  }
}
