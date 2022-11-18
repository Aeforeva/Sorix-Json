package com.example.sorixjson.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class JsonViewModel : ViewModel() {

    private val _testStr = MutableLiveData<String>("TEST 01")
    val testStr: LiveData<String> = _testStr

    private val _testNum = MutableLiveData<Int>(0)
    val testNum: LiveData<Int> = _testNum

    fun incNum() {
        _testNum.value = _testNum.value?.plus(1)
    }
}
