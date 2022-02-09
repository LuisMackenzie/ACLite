package com.mackenzie.aclite.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class LoginRepository(
    private val loginRemoteDataSource: LoginRemoteDataSource = LoginRemoteDataSourceImpl()
) {

    public suspend fun tryLogin(user:String, pass:String):LoginResult =
        loginRemoteDataSource.tryLogin(user, pass)

}

