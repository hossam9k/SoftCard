package com.surepay.auth_domain.di

import com.surepay.auth_domain.repositpry.LoginRepository
import com.surepay.auth_domain.user_case.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object LoginDomainModule {

    @ViewModelScoped
    @Provides
    fun provideLoginUseCase(
        loginRepository: LoginRepository
    ): LoginUseCase{
        return LoginUseCase(loginRepository)
    }

}