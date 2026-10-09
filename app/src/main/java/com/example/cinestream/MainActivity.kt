package com.example.cinestream

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cinestream.data.remote.RetrofitInstance
import com.example.cinestream.data.vm.HomeVM
import com.example.cinestream.data.vm.HomeVMFactory
import com.example.cinestream.ui.AppUI.HomeScree.HomePage
import com.example.cinestream.ui.theme.CineStreamTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CineStreamTheme {

                val homeVM: HomeVM = viewModel(
                    factory = HomeVMFactory(
                        api = RetrofitInstance.api
                    )
                )

                LaunchedEffect(Unit) {
                    homeVM.gettALltheData()
                }

                HomePage(homeVM = homeVM)
            }
        }
    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CineStreamTheme {
        Greeting("Android")
    }
}