package com.mehmettekin.firebasefirestoreproject.screens


import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mehmettekin.firebasefirestoreproject.R
import com.mehmettekin.firebasefirestoreproject.data.Resource
import com.mehmettekin.firebasefirestoreproject.navigation.Screen
import com.mehmettekin.firebasefirestoreproject.viewmodels.AuthViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(navController: NavController, viewModel: AuthViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 36.dp, bottom = 36.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val focusManager = LocalFocusManager.current
        val keyboardController = LocalSoftwareKeyboardController.current

        var email by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }
        var passwordVisibility by rememberSaveable { mutableStateOf(false) }

        val isEmailValid by derivedStateOf { Patterns.EMAIL_ADDRESS.matcher(email).matches() }
        val isPasswordValid by derivedStateOf { password.length > 5 }

        val loginFlow = viewModel.loginFlow.collectAsState()
        val context = LocalContext.current

        Icon(
            painter = painterResource(id = R.drawable.safebox),
            contentDescription = "login icon",
            modifier = Modifier
                .size(200.dp, 200.dp)
                .padding(bottom = 32.dp),
            tint = Color.Red
        )

        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email Address") },
                placeholder = { Text(text = "abc@domain.com") },
                singleLine = true,
                shape = RoundedCornerShape(corner = CornerSize(15.dp)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 6.dp),
                trailingIcon = {
                    if (email.isNotBlank()) {
                        IconButton(onClick = { email = "" }) {
                            Icon(
                                painter = painterResource(id = R.drawable.clear),
                                contentDescription = "clear email"
                            )
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                    autoCorrect = true
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                isError = !isEmailValid
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                shape = RoundedCornerShape(corner = CornerSize(15.dp)),
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(
                            painter = painterResource(id = if (passwordVisibility) R.drawable.visibility_on else R.drawable.visibility_off),
                            contentDescription = "password visibility"
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                    autoCorrect = true
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.clearFocus() }
                ),
                isError = !isPasswordValid,
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
            )

        }

        Button(
            onClick = {

                keyboardController?.hide()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            enabled = isEmailValid && isPasswordValid,
            shape = RoundedCornerShape(corner = CornerSize(30.dp)),
            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Red,
                contentColor = Color.White,
                disabledContentColor = Color.White),

            ) {
            Text(text = "Login")
        }

        TextButton(onClick = { navController.navigate(Screen.Signup.route) }) {
            Text(text = stringResource(id = R.string.signup))
        }


        loginFlow.value?.let {
            when(it){
                is Resource.Failure -> {
                    Toast.makeText(context,it.exception.message, Toast.LENGTH_LONG).show()
                }
                Resource.Loading -> {
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                    }

                }
                is Resource.Success -> {
                    LaunchedEffect(Unit){
                        navController.navigate(Screen.Main.route){
                            popUpTo(Screen.Login.route){ inclusive = true}
                        }
                    }
                }
            }
        }


    }
}