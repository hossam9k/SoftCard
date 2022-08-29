package com.surepay.auth_data.repository

import com.surepay.auth_data.mapper.toLoginDomain
import com.surepay.auth_data.remote.LoginApi
import com.surepay.auth_domain.login_model.Login
import com.surepay.auth_domain.repositpry.LoginRepository
import com.surepay.core.util.Resource

class LoginRepositoryImpl (
    private val authApi: LoginApi
        ): LoginRepository {

    override suspend fun login(email: String, password: String): Resource<Login> {
       return try {
           Resource.Success(
               data = authApi.login(
                   email= email,
                   password = password
               ).toLoginDomain()
           )
       }catch(e: Exception) {
           e.printStackTrace()
           Resource.Error(e.message ?: "An unknown error occurred.")
       }
    }
}
