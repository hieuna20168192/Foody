package com.example.foody.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class VMStatus {
    LOADING,
    ERROR,
    IS_EMPTY,
    DONE
}

abstract class BaseViewModel : ViewModel() {
    protected val _status = MutableLiveData<VMStatus>()
    val status: LiveData<VMStatus> = _status
}
