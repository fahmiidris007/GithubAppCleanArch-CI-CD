package com.fahmiproduction.githubappcleanarch.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.fahmiproduction.githubappcleanarch.core.domain.model.User
import com.fahmiproduction.githubappcleanarch.core.domain.usecase.UserUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    fun getDetailUser(username: String) =
        userUseCase.getDetailUser(username).asLiveData()

    fun getFavoriteState(username: String) =
        userUseCase.getFavoriteState(username)?.asLiveData()

    fun insertFavoriteUser(user: User) = viewModelScope.launch {
        userUseCase.insertFavoriteUser(user)
    }

    fun deleteFavoriteUser(user: User) = viewModelScope.launch {
        userUseCase.deleteFavoriteUser(user)
    }
}
