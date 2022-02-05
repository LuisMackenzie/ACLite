package com.mackenzie.aclite.data

import kotlinx.coroutines.delay

class LoginRepository(
    private val loginRemoteDataSource: LoginRemoteDataSource = LoginRemoteDataSource()
) {

    public suspend fun tryLogin(user:String, pass:String):LoginResult =
        loginRemoteDataSource.tryLogin(user, pass)

}

