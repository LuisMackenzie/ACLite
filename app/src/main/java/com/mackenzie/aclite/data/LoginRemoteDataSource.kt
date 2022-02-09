package com.mackenzie.aclite.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

interface LoginRemoteDataSource {

    public suspend fun tryLogin(user:String, pass:String):LoginResult

}

class LoginRemoteDataSourceImpl: LoginRemoteDataSource {

    public override suspend fun tryLogin(user:String, pass:String):LoginResult = withContext(Dispatchers.IO) {


        delay(2000)
        // Aqui suspendemos el hilo principal
        // Thread.sleep(2000)

        // Primera forma de estructurar una validacion
        /*val userError = !user.contains('@')
        val passError = pass.length < 6
        return LoginResult(userError, passError)*/

        // Segunda forma de estructurar una validacion
        LoginResult(
            userError = !user.contains('@'),
            passError = pass.length < 6
        )

        // Aqui agregamos las validaciones inline
        // return LoginResult(!user.contains('@'), pass.length < 6)
    }

}