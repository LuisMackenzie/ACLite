package com.mackenzie.aclite

import com.mackenzie.aclite.data.LoginRemoteDataSource
import com.mackenzie.aclite.data.LoginRemoteDataSourceImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LoginRemoteDataSourceTest {

    private val ds = LoginRemoteDataSourceImpl()

    @Test
    public fun `User without @ returns Error`() = runBlocking {
        val result = ds.tryLogin("user", "")
        assertTrue(result.userError)
    }

    @Test
    public fun `User with @ returns Success`() = runBlocking {
        val result = ds.tryLogin("user@", "")
        assertFalse(result.userError)
    }

    @Test
    public fun `Pass with less than 6 Chars returns Error`():Unit = runBlocking {
        val result = ds.tryLogin("", "1234")
        assertTrue(result.passError)
    }

    @Test
    public fun `Pass with more than 6 Chars returns Success`():Unit = runBlocking {
        val result = ds.tryLogin("", "123456")
        assertFalse(result.passError)
    }

}