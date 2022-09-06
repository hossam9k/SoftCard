package com.surepay.auth_domain.use_case

import com.google.common.truth.Truth.assertThat
import com.surepay.auth_domain.model.Login
import com.surepay.auth_domain.repositpry.AuthRepository
import com.surepay.auth_domain.use_case.utils.isValidEmail
import com.surepay.core.util.Resource
import io.mockk.InternalPlatformDsl.toStr
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test


class LoginUseCaseTest {

    @MockK
    private lateinit var authRepository: AuthRepository

    private lateinit var loginUseCase: LoginUseCase


    @Before
    fun setup(){
        authRepository = mockk()
        loginUseCase = LoginUseCase(authRepository)
    }


    @Test
    fun `when invoke with invalid email then return empty error state`()= runBlocking{
       //arrange
        val email = "aa.a"
       //act
        val actual = loginUseCase.invoke(email, "")
        val expected = Resource.Error("")
      // assert
        assertThat(actual).isEqualTo(expected)
    }


    @Test
    fun `when invoke with valid credential then return login model`()= runBlocking{
        //arrange
        val email = "aa@a.a"
        val password  = "123456"
        //act
        val expected = Resource.Success(Login(true,1,"",1))
        coEvery { authRepository.login(email,password) }.returns(expected)
        val actual = loginUseCase.invoke(email, password)
        // assert
        assertThat(actual).isEqualTo(expected)
    }



    @Test
    fun `when invoke with Server Error email then return empty error state`()= runBlocking{
        //arrange
        val email = "aa.a"
        val password = "11"
        //act
        val expected  = Resource.Error("Error Unauthorized")
        coEvery { authRepository.login(email,password) }.returns(expected)
        val actual = Resource.Error("Error Unauthorized")

        // assert
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `login use case success`(){

        val loginSuccess :Resource<Login> =  Resource.Success(Login(true,1,"title",1))
        runBlocking {
            coEvery { authRepository.login("email@email.com","76567") } returns loginSuccess
        }

        assertThat(loginSuccess).isEqualTo(loginSuccess)
    }



    @After
    fun tearDown(){

    }
}