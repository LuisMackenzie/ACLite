package com.mackenzie.aclite

import com.mackenzie.aclite.data.LoginRemoteDataSource
import com.mackenzie.aclite.data.LoginRemoteDataSourceImpl
import com.mackenzie.aclite.data.LoginRepository
import com.mackenzie.aclite.data.LoginResult
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LoginRepositoryTest {

    private  lateinit var ds: FakeLoginRemoteDS
    private lateinit var repository: LoginRepository

    @Before
    fun setUp() {
        ds = FakeLoginRemoteDS()
        repository = LoginRepository(ds)
    }

    @Test
    fun `check errors are passed to the repository`(): Unit = runBlocking {
        ds.userError = true
        ds.passError = true
        val result = repository.tryLogin("", "")
        Assert.assertTrue(result.userError)
        Assert.assertTrue(result.passError)
    }

}

class FakeLoginRemoteDS(var userError:Boolean=false,var passError:Boolean=false): LoginRemoteDataSource {
    override suspend fun tryLogin(user: String, pass: String): LoginResult {
        return LoginResult(userError, passError)
    }

}