package com.example.cinestream.data.vm

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinestream.data.Info.HomeResponse
import com.example.cinestream.data.Repo.AnimeRepository
import kotlinx.coroutines.launch

class HomeVM(private val repo: AnimeRepository)
    : ViewModel(){


    var gettAllHomePageContent: HomeResponse =

    fun gettALltheData(): HomeResponse{
        viewModelScope.launch{
            val response=repo.getTHeHomePage()
        }
        return response
    }
}