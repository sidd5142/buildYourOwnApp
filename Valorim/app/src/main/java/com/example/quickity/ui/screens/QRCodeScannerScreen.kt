package com.example.quickity.ui.screens

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QRCodeScannerScreen(urlText: String, onUrlTextUpdate: (String) -> Unit) {

    var statusText by remember { mutableStateOf("") }
    val context = LocalContext.current

    PermissionRequestDialog(
        permission = Manifest.permission.CAMERA,
        onResult = { isGranted ->
            statusText = if (isGranted) {
                "Scan QR code now!"
            } else {
                "No camera permission!"
            }
        },
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .verticalScroll(rememberScrollState())
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text=statusText, fontWeight = FontWeight.SemiBold, fontSize = 30.sp)

        Spacer(modifier = Modifier.height(5.dp))
        CameraPreview { url ->
            onUrlTextUpdate(url)
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = urlText,
            onValueChange = {},
            label = {Text("Detected URL")},
            readOnly = true,
        )

        Spacer(modifier = Modifier.height(5.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                launchUrl(context, urlText)
            }
        ) {
            Text(text="Launch", fontWeight = FontWeight.SemiBold, fontSize = 30.sp)
        }
    }
}

fun launchUrl(context: Context, urlText: String) {
    val uri: Uri = Uri.parse(urlText)

    val intent = Intent(Intent.ACTION_VIEW, uri).apply {
        setPackage("com.android.chrome")
    }

    try {
        context.startActivity(intent)

    } catch (e: ActivityNotFoundException) {
        intent.setPackage(null)

        try {
            context.startActivity(intent)

        } catch (e: ActivityNotFoundException) {

        }
    }
}