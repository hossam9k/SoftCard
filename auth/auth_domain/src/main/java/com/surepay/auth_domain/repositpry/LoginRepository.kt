package com.surepay.auth_domain.repositpry
import com.surepay.auth_domain.login_model.Login
import com.surepay.core.util.Resource


interface LoginRepository {

    suspend fun login(
        email: String,
        password: String
    ): Resource<Login>
}