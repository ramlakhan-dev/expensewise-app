package com.rl.expensewise.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.rl.expensewise.data.repository.AuthRepositoryImpl
import com.rl.expensewise.domain.repository.AuthRepository
import com.rl.expensewise.domain.usecase.auth.AuthUseCase
import com.rl.expensewise.domain.usecase.auth.GetCurrentUserUseCase
import com.rl.expensewise.domain.usecase.auth.SendResetPasswordLinkUseCase
import com.rl.expensewise.domain.usecase.auth.SignInUseCase
import com.rl.expensewise.domain.usecase.auth.SignOutUseCase
import com.rl.expensewise.domain.usecase.auth.SignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth, firebaseFirestore: FirebaseFirestore): AuthRepository =
        AuthRepositoryImpl(
            firebaseAuth = firebaseAuth,
            firebaseFirestore = firebaseFirestore
        )

    @Provides
    @Singleton
    fun provideAuthUseCase(authRepository: AuthRepository): AuthUseCase = AuthUseCase(
        signUp = SignUpUseCase(authRepository),
        signIn = SignInUseCase(authRepository),
        sendResetPasswordLink = SendResetPasswordLinkUseCase(authRepository),
        signOut = SignOutUseCase(authRepository),
        getCurrentUser = GetCurrentUserUseCase(authRepository)
    )
}