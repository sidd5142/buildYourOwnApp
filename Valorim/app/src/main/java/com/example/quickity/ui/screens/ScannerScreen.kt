package com.example.quickity.ui.screens

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Size
import android.widget.Button
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.quickity.R
import com.example.quickity.ui.theme.blackV
import com.example.quickity.ui.theme.blueV
import com.example.quickity.ui.theme.valorantFont
import com.google.accompanist.systemuicontroller.SystemUiController
import java.util.jar.Manifest


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScannerScreen(
    navController: NavController,
    useSystemUiController: Boolean = true,
    urlText:String,
    onUrlTextUpdate: (String) -> Unit
) {
    var statusText by remember { mutableStateOf("") }
    val context = LocalContext.current

    // Not using permission dialog
    /*PermissionRequestDialog(
        permission = android.Manifest.permission.CAMERA,
        onResult = { isGranted ->
            statusText = if (isGranted) {
                "Scan QR code now!"
            } else {
                "No camera permission!"
            }
        },
    )*/

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = blackV)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = statusText, fontWeight = FontWeight.SemiBold, fontSize = 20.sp)

        AnimatedPreloaderScan(
            modifier = Modifier.height(80.dp).fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(5.dp))

        Card(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            CameraPreview { url ->
                onUrlTextUpdate(url)
            }
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = urlText,
            colors = TextFieldDefaults.textFieldColors(

                containerColor = blackV
            ),
            onValueChange = {},
            shape = RoundedCornerShape(20.dp),
            label = { Text("Detected URL", fontFamily = valorantFont) },
            readOnly = true,
        )

        Spacer(modifier = Modifier.height(5.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 32.dp)
                .padding(bottom = 16.dp)
                .height(50.dp),
            onClick = {
                launchUrl(context, urlText)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = blueV
            )
        ) {
            Text(text = "Launch", fontWeight = FontWeight.SemiBold, fontSize = 30.sp,fontFamily = valorantFont,)
        }
    }
}

@Composable
fun AnimatedPreloaderScan(modifier: Modifier = Modifier) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.scananim1
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )

    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress,
        modifier = modifier
    )
}
