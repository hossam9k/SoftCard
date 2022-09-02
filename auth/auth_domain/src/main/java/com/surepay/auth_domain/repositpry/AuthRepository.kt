package com.surepay.auth_domain.repositpry
import com.surepay.auth_domain.model.Login
import com.surepay.core.util.Resource
import kotlinx.coroutines.flow.Flow


interface AuthRepository {

    suspend fun login(
        email: String,
        password: String
    ): Resource<Login>
}