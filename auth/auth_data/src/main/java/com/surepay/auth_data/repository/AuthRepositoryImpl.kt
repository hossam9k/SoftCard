package com.surepay.auth_data.repository

import com.surepay.auth_data.mapper.toLoginDomain
import com.surepay.auth_data.remote.AuthApi
import com.surepay.auth_domain.model.Login
import com.surepay.auth_domain.repositpry.AuthRepository
import com.surepay.core.util.Resource
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl (
    private val authApi: AuthApi
        ): AuthRepository {

    override suspend fun login(email: String, password: String): Resource<Login> {
       return try {
           Resource.Success(
               data = authApi.login(
//                   email= email,
//                   password = password
               ).toLoginDomain()
           )
       }catch(e: Exception) {
           e.printStackTrace()
           Resource.Error(e.message ?: "An unknown error occurred.")
       }
    }
}
