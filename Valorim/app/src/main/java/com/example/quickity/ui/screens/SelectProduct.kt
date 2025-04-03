package com.example.quickity.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

// This contains UI screen for selecting a product

@Composable
fun SelectProduct() {
    Box(modifier = androidx.compose.ui.Modifier.fillMaxSize()){
        Column {
            Text(text = "Select Product")
        }
    }
}