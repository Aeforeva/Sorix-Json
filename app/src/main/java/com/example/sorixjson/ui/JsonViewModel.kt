package com.example.sorixjson.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sorixjson.model.Applicable
import com.example.sorixjson.model.InputData
import com.example.sorixjson.model.SomeData
import com.example.sorixjson.network.SorixApi
import kotlinx.coroutines.launch

enum class SorixApiStatus { LOADING, ERROR, DONE, NONE }

class JsonViewModel : ViewModel() {

    private val _status = MutableLiveData<SorixApiStatus>()
    val status: LiveData<SorixApiStatus> = _status

    private val _sendStatus = MutableLiveData<SorixApiStatus>()
    val sendStatus: LiveData<SorixApiStatus> = _sendStatus

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> = _errorMsg

    private val _sorixJson = MutableLiveData<SomeData>()
    val sorixJson: LiveData<SomeData> = _sorixJson

    private val _applicables = MutableLiveData<List<Applicable>>()
    val applicables: LiveData<List<Applicable>> = _applicables

    private val _applicable = MutableLiveData<Applicable>()
    val applicable: LiveData<Applicable> = _applicable

    var inputData = InputData()
    val outPutString = mutableListOf<String>()
    val statusCode = MutableLiveData<Int>()

    // We can call getJson() here or in fragment
    init {
        getJson()
    }

    fun getJson() {
        viewModelScope.launch {
            _status.value = SorixApiStatus.LOADING
            try {
                _sorixJson.value = SorixApi.retrofitService.getSorixJson()
                Log.i("Sorix API JSON", sorixJson.value.toString())
                _applicables.value = sorixJson.value?.networks?.applicable
                Log.i("Sorix API applicables", applicables.value.toString())
                _status.value = SorixApiStatus.DONE
            } catch (e: Exception) {
                _errorMsg.value = e.message
                Log.i("Sorix API ERROR", errorMsg.value.toString())
                _status.value = SorixApiStatus.ERROR
            }
        }
    }

    fun postResponse() {
        viewModelScope.launch {
            _sendStatus.value = SorixApiStatus.LOADING
            try {
                val post = SorixApi.retrofitServiceSend.postObj(inputData)
                statusCode.value = post.code()
                _sendStatus.value = SorixApiStatus.DONE
                Log.i("Response", post.toString())
            } catch (e: Exception) {
                _errorMsg.value = e.message
                _sendStatus.value = SorixApiStatus.ERROR
                Log.i("Sorix API ERROR", e.toString())
            } finally {
                _sendStatus.value = SorixApiStatus.NONE
            }
        }
    }

    fun putResponse() {
        viewModelScope.launch {
            _sendStatus.value = SorixApiStatus.LOADING
            try {
                val put = SorixApi.retrofitServiceSend.putObj(inputData)
                statusCode.value = put.code()
                _sendStatus.value = SorixApiStatus.DONE
                Log.i("Response", put.toString())
            } catch (e: Exception) {
                _errorMsg.value = e.message
                _sendStatus.value = SorixApiStatus.ERROR
                Log.i("Sorix API ERROR", e.toString())
            } finally {
                _sendStatus.value = SorixApiStatus.NONE
            }
        }
    }

    fun onApplicableClicked(applicable: Applicable) {
        _applicable.value = applicable
    }
}
