package com.fahmiproduction.githubappcleanarch.di


import com.fahmiproduction.githubappcleanarch.core.domain.usecase.UserInteractor
import com.fahmiproduction.githubappcleanarch.core.domain.usecase.UserUseCase
import com.fahmiproduction.githubappcleanarch.detail.DetailViewModel
import com.fahmiproduction.githubappcleanarch.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<UserUseCase> { UserInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}