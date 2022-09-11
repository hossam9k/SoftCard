package com.surepay.auth_domain.use_case

import com.surepay.auth_domain.model.Login
import com.surepay.auth_domain.repositpry.AuthRepository
import com.surepay.auth_domain.use_case.utils.isValidEmail
import com.surepay.auth_domain.exeptions.EmailValidationException
import com.surepay.auth_domain.use_case.utils.isEmailNotBlank
import com.surepay.auth_domain.use_case.utils.isPasswordNotBlank
import com.surepay.core.util.Resource

class LoginUseCase(
    private val authRepository: AuthRepository
    ){
        suspend operator fun invoke(
        email: String,
        password: String
        ):Resource<Login>{
            if (!isEmailNotBlank(email) && !isPasswordNotBlank(password)){
                return Resource.Error(EmailValidationException.EmptyEmail, message = null)
            }

            if(!isValidEmail(email)){
                return Resource.Error(error=  EmailValidationException.InvalidEmail , message = null)
            }

            return authRepository.login(email,password)

        }


}