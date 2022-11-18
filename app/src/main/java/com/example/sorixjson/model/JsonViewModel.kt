package com.example.sorixjson.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

enum class SorixApiStatus { LOADING, ERROR, DONE }

class JsonViewModel : ViewModel() {

    private val _status = MutableLiveData<SorixApiStatus>()
    val status: LiveData<SorixApiStatus> = _status

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> = _errorMsg

    private val _sorixJson = MutableLiveData<SomeData>()
    val sorixJson: LiveData<SomeData> = _sorixJson

    private val _applicables = MutableLiveData<List<Applicable>>()
    val applicables: LiveData<List<Applicable>> = _applicables

    private val _applicable = MutableLiveData<Applicable>()
    val applicable: LiveData<Applicable> = _applicable

//    init {
//        getJson()
//    }

    fun getJson() {
        viewModelScope.launch {
            _status.value = SorixApiStatus.LOADING
            try {
                _sorixJson.value = SorixApi.retrofitService.getSorixJson()
                Log.i("Sorix API JSON", _sorixJson.value.toString())
                _applicables.value = _sorixJson.value?.networks?.applicable
                Log.i("Sorix API applicables",  _applicables.value.toString())
                _status.value = SorixApiStatus.DONE
            } catch (e: Exception) {
                _errorMsg.value = e.message
                Log.i("Sorix API ERROR", _errorMsg.value.toString())
                _status.value = SorixApiStatus.ERROR
            }
        }
    }

    fun onApplicableClicked(applicable: Applicable) {
        _applicable.value = applicable
    }
}
