package com.example.cinestream.data.vm

import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinestream.data.Info.HomeResponse
import com.example.cinestream.data.Repo.AnimeRepository
import kotlinx.coroutines.launch

class HomeVM(private val repo: AnimeRepository)
    : ViewModel(){


    var gettAllHomePageContent by mutableStateOf<HomeResponse?>(null)
        private set

    fun gettALltheData(){
        viewModelScope.launch{
            val response=repo.getTHeHomePage()
            gettAllHomePageContent = response
        }

    }
}