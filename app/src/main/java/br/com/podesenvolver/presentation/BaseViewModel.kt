package br.com.podesenvolver.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    fun launch(errorBlock: ((Throwable) -> Unit)? = null, block: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                block()
            } catch (throwable: Throwable) {
                Log.e("launch", throwable.message, throwable)
                errorBlock?.invoke(throwable)
            }
        }
    }
}
