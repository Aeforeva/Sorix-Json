package com.example.sorixjson.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class JsonViewModel : ViewModel() {

    private val _status = MutableLiveData<String>("TEST 01")
    val status: LiveData<String> = _status

    private val _testNum = MutableLiveData<Int>(0)
    val testNum: LiveData<Int> = _testNum

    fun incNum() {
        _testNum.value = _testNum.value?.plus(1)
    }

    init {
        getJson()
    }

    private fun getJson() {
        viewModelScope.launch {
            try {
                val listResult = SorixApi.retrofitService.getJson()
                _status.value = listResult.toString()
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }
}
