package com.mackenzie.aclite.domain

import com.mackenzie.aclite.data.LoginRepository
import com.mackenzie.aclite.data.LoginResult

class TryLoginUseCase(
    private val repository:LoginRepository = LoginRepository()
) {

    public suspend operator fun invoke(user:String, pass:String):LoginResult =
        repository.tryLogin(user, pass)

}