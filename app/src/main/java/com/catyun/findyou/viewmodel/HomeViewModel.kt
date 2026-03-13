package com.catyun.findyou.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    var roomCode by mutableStateOf("")
        private set

    fun updateRoomCode(code: String) {
        roomCode = code.uppercase()
    }
}
