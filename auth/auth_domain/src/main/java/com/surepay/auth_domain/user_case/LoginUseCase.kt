package com.surepay.auth_domain.user_case

import com.surepay.auth_domain.login_model.Login
import com.surepay.auth_domain.repositpry.LoginRepository
import com.surepay.core.util.Resource

class LoginUseCase(
    private val authRepository: LoginRepository
    ){
        suspend operator fun invoke(
        email: String,
        password: String
        ):Resource<Login>{
            return authRepository.login(email,password)

        }


}