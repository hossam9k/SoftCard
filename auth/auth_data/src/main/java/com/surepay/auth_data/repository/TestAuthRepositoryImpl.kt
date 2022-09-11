package com.surepay.auth_data.repository

import com.surepay.auth_data.remote.AuthApi
import com.surepay.auth_domain.model.Login
import com.surepay.auth_domain.repositpry.AuthRepository
import com.surepay.core.util.Resource
import kotlinx.coroutines.delay
import java.net.UnknownHostException

class TestAuthRepositoryImpl (
    private val authApi: AuthApi
): AuthRepository {
    override suspend fun login(email: String, password: String): Resource<Login> {
        delay(1000*2)
        val data = Resource.Success(Login(true,1,"",1))
//        throw UnknownHostException("Fake No internet")
        return data;
        
    }
}