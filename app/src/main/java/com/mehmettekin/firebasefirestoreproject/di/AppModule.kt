package com.mehmettekin.firebasefirestoreproject.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mehmettekin.firebasefirestoreproject.data.repository.AuthRepository
import com.mehmettekin.firebasefirestoreproject.data.repository.AuthRepositoryImpl
import com.mehmettekin.firebasefirestoreproject.data.repository.CreateUserRepository
import com.mehmettekin.firebasefirestoreproject.data.repository.CreateUserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    fun provideCreateUserImpl(impl: CreateUserRepositoryImpl): CreateUserRepository = impl

}