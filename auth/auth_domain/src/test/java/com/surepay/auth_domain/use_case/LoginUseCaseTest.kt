package com.surepay.auth_domain.use_case

import com.surepay.auth_domain.repositpry.AuthRepository
import io.mockk.mockk
import org.junit.Test

class LoginUseCaseTest {


    @Test
    fun `login email and password are properly empty`(){
        val authRepository = mockk<AuthRepository>()

    }

    @Test
    fun `login email and password success`(){

    }
}