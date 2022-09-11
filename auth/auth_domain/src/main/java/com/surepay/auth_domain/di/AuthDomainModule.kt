package com.surepay.auth_domain.di

import com.surepay.auth_domain.repositpry.AuthRepository
import com.surepay.auth_domain.use_case.LoginUseCase
import com.surepay.core.domain.qualifier.TestQualifier
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
        @TestQualifier authRepository: AuthRepository
    ): LoginUseCase{
        return LoginUseCase(authRepository)
    }

}