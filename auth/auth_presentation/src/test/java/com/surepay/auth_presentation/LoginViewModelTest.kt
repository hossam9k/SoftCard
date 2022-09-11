package com.surepay.auth_presentation

import com.google.common.truth.Truth.assertThat
import com.surepay.auth_domain.exeptions.LoginException
import com.surepay.auth_domain.model.Login
import com.surepay.auth_domain.use_case.LoginUseCase
import com.surepay.auth_presentation.login.LoginViewModel
import com.surepay.core.util.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@OptIn(DelicateCoroutinesApi::class)
@ExperimentalTime
class LoginViewModelTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    @MockK(relaxed = true)
    private lateinit var loginUseCase: LoginUseCase

    private lateinit var loginViewModel : LoginViewModel

    //private lateinit var state : LoginState

    @Before
    fun setup(){
        MockKAnnotations.init(this)
        Dispatchers.setMain(mainThreadSurrogate)
        //loginUseCase = mockk()
        loginViewModel = LoginViewModel(loginUseCase)
       // state = loginViewModel.state

    }

    @Test
    fun `login and return success`() = runTest {
        val email = "s@s.com"
        val password = "12345678"
        val  login = Login(true,1,"name",2)
        val expcted_loginResponse :Resource<Login> = Resource.Success(login)

        coEvery { loginUseCase.invoke(email,password) }.returns(expcted_loginResponse)

        val actual_loginResponse :Resource<Login> = Resource.Success(login)

        assertThat(actual_loginResponse).isEqualTo(expcted_loginResponse)
    }

    @Test
    fun `login and return error`() = testCoroutineRule.runTest{
        val email = "s@s.com"
        val password = "12345678"
        val expcted_loginResponse :Resource<Login> = Resource.Error(error = LoginException.UNAUTHORIZED,  message = null)

        coEvery { loginUseCase.invoke(email,password) }.returns(expcted_loginResponse)

        val actual_loginResponse :Resource<Login> = Resource.Error(error = LoginException.UNAUTHORIZED, message = null)

        assertThat(actual_loginResponse).isEqualTo(expcted_loginResponse)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

}