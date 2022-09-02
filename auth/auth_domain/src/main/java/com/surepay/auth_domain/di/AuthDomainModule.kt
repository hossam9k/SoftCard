package com.surepay.auth_domain.di

import com.surepay.auth_domain.repositpry.AuthRepository
import com.surepay.auth_domain.user_case.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AuthDomainModule {

    @ViewModelScoped
    @Provides
    fun provideLoginUseCase(
        authRepository: AuthRepository
    ): LoginUseCase{
        return LoginUseCase(authRepository)
    }

}