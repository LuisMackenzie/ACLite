package com.mackenzie.aclite.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mackenzie.aclite.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _state = MutableLiveData<UiState>(UiState())
    public val state:LiveData<UiState> get() = _state

    public fun onTryLogin(user:String, pass: String) {
        viewModelScope.launch {
            _state.value = UiState(loggingIn = true)
            tryLogin(user, pass)

        }
    }

    private suspend fun tryLogin(user:String, pass:String) {
        delay(2000)
        val userError = if (!user.contains('@')) R.string.username_error else null
        val passError = if (pass.length < 6) R.string.password_error else null
        val loggedIn = userError == null && passError == null
        _state.value = UiState(
            loggedIn = loggedIn,
            userError = userError,
            passError = passError
        )


    }

    fun onNavigateToNextScreen() {
        _state.value = requireNotNull(_state.value).copy(loggedIn = false)
    }

    data class UiState(
        val loggingIn:Boolean = false,
        val loggedIn:Boolean = false,
        val userError:Int? = null,
        val passError:Int? = null
        )


    sealed class UiStateOld {
        object Loading: UiStateOld()
        data class Error(val userError: String, val passError:String): UiStateOld()
        object LoggedIn: UiStateOld()
    }

}