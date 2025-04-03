package com.example.quickity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.quickity.ui.screens.HolderScreen
import com.example.quickity.ui.theme.QuickityTheme
import com.example.quickity.ui.theme.blackV

const val URL_KEY: String = "UrlKey"
var urlText by mutableStateOf("")
//@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuickityTheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,

                ) {

                    Box(
                        modifier = Modifier.fillMaxSize().background(color = blackV)
                    ) {
                        HolderScreen() //HolderScreen contains Compose navigation logic
                    }
                }
            }
        }
    }

    // Save and restore urlText
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(URL_KEY, urlText)
        super.onSaveInstanceState(outState) // need to be called last
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val restoreUrlText = savedInstanceState.getString(URL_KEY)

        if(restoreUrlText != null) urlText = restoreUrlText
    }
    
}
