package com.catyun.findyou.viewmodel.home

data class HomeUiState(
    val roomCode: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
