package com.petarmarijanovic.rxactivityresult;

import android.app.Activity;
import android.content.Intent;

/** Created by petar on 26/07/2017. */
public class ActivityResult {
  private int resultCode;
  private Intent data;

  public ActivityResult(int resultCode, Intent data) {
    this.resultCode = resultCode;
    this.data = data;
  }

  public int getResultCode() {
    return resultCode;
  }

  public Intent getData() {
    return data;
  }

  public boolean isOk() {
    return resultCode == Activity.RESULT_OK;
  }

  public boolean isCanceled() {
    return resultCode == Activity.RESULT_CANCELED;
  }

  public boolean isFirstUser() {
    return resultCode == Activity.RESULT_FIRST_USER;
  }

  @Override
  public int hashCode() {
    int result = resultCode;
    result = 31 * result + (data != null ? data.hashCode() : 0);
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ActivityResult)) return false;

    ActivityResult that = (ActivityResult) o;

    if (resultCode != that.resultCode) return false;
    return data != null ? data.equals(that.data) : that.data == null;
  }

  @Override
  public String toString() {
    return "ActivityResult{" + "resultCode=" + resultCode + ", data=" + data + '}';
  }
}
