package com.mehmettekin.firebasefirestoreproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mehmettekin.firebasefirestoreproject.navigation.AppNavHost
import com.mehmettekin.firebasefirestoreproject.ui.theme.FirebaseFireStoreProjectTheme
import com.mehmettekin.firebasefirestoreproject.viewmodels.AuthViewModel
import com.mehmettekin.firebasefirestoreproject.viewmodels.CreateUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<AuthViewModel>()
    private val userViewModel by viewModels<CreateUserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseFireStoreProjectTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                        AppNavHost(viewModel = viewModel, userViewModel = userViewModel)
                }
            }
        }
    }
}
