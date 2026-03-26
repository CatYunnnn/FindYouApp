package com.catyun.findyou.viewmodel.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    var uiState by mutableStateOf(HomeUiState())
        private set

    // 使用者輸入
    fun onRoomCodeChange(value: String) {
        uiState = uiState.copy(roomCode = value, errorMessage = null)
    }

    // 點擊Join
    fun onJoinClick(): Boolean =
        if (uiState.roomCode.length < 6) {
            uiState = uiState.copy(errorMessage = "Room code must be 6 digits")
            false
        } else {
            true
        }
}
