package com.example.cinestream.data.vm

import android.util.Log
import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cinestream.data.Info.HomeResponse
import com.example.cinestream.data.Repo.AnimeRepository
import com.example.cinestream.data.remote.AnimeApi
import kotlinx.coroutines.launch

class HomeVM(private val repo: AnimeRepository)
    : ViewModel(){


    var gettAllHomePageContent by mutableStateOf<HomeResponse?>(null)
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun gettALltheData() {
        viewModelScope.launch {
            try {
                Log.d("HomeVM", "Starting API request")

                val response = repo.getTHeHomePage()

                gettAllHomePageContent = response
                response.results.trending.forEach { anime ->
                    Log.d(
                        "PosterDebug",
                        "Title: ${anime.title}, Poster: ${anime.poster}"
                    )
                }

                Log.d(
                    "HomeVM",
                    "API success: ${response.success}, " +
                            "trending count: ${response.results.trending.size}"
                )
            } catch (e: Exception) {
                Log.e("HomeVM", "API request failed", e)
            }
        }
    }
}
class HomeVMFactory(
    private val api: AnimeApi
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        if (modelClass.isAssignableFrom(HomeVM::class.java)) {
            val repository = AnimeRepository(api)

            @Suppress("UNCHECKED_CAST")
            return HomeVM(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}