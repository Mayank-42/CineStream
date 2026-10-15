package com.example.cinestream.data.vm

import android.util.Log
import android.util.Log.e
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cinestream.data.Info.Trending
import com.example.cinestream.data.Info.TrendingResponse
import com.example.cinestream.data.Repo.Repository
import com.example.cinestream.data.remote.Api
import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.launch

class HomeVM(private val repo: Repository)
    : ViewModel(){


    var gettAllHomePageContent by mutableStateOf<Flow<TrendingResponse>?>(null)

        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun gettALltheData() {
        viewModelScope.launch {

            try {
                gettAllHomePageContent = repo.getTHeHomePage()
                errorMessage = null
            } catch (e: Exception) {
                errorMessage = e.message ?: "Something went wrong"
            }



        }
    }
}
class HomeVMFactory(
    private val api: Api
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        if (modelClass.isAssignableFrom(HomeVM::class.java)) {
            val repository = Repository(api)

            @Suppress("UNCHECKED_CAST")
            return HomeVM(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}