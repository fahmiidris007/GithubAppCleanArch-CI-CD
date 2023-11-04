package com.fahmiproduction.githubappcleanarch.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fahmiproduction.githubappcleanarch.core.domain.usecase.UserUseCase

class FavoriteViewModel(userUseCase: UserUseCase) : ViewModel() {
    val favoriteUser = userUseCase.getFavoriteUser().asLiveData()
}
