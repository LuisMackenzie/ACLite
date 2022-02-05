package com.mackenzie.aclite.data

import kotlinx.coroutines.delay

class LoginRemoteDataSource {

    public suspend fun tryLogin(user:String, pass:String):LoginResult {
        delay(2000)
        // Primera forma de estructurar una validacion
        /*val userError = !user.contains('@')
        val passError = pass.length < 6
        return LoginResult(userError, passError)*/

        // Segunda forma de estructurar una validacion
        return LoginResult(
            userError = !user.contains('@'),
            passError = pass.length < 6
        )

        // Aqui agregamos las validaciones inline
        // return LoginResult(!user.contains('@'), pass.length < 6)
    }

}