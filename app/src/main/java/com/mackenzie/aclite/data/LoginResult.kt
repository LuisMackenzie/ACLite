package com.mackenzie.aclite.data

data class LoginResult(val userError:Boolean, val passError:Boolean) {

    val success get() = !userError && !passError

}
