package com.petarmarijanovic.rxactivityresult.sample

enum class ApiStatus(val textId: Int, val colorId: Int) {
    NOT_CHECKED(R.string.result_not_checked, R.color.lightGray),
    OK(R.string.result_ok, R.color.green),
    CANCELED(R.string.result_canceled, R.color.red)
}