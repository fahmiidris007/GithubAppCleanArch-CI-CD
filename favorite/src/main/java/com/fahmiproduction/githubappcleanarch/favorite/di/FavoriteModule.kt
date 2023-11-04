package com.fahmiproduction.githubappcleanarch.favorite.di

import com.fahmiproduction.githubappcleanarch.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteViewModelModule = module {
    viewModel { FavoriteViewModel(get()) }
}