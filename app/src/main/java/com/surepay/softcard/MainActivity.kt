package com.surepay.softcard

import android.annotation.SuppressLint
import androidx.navigation.compose.NavHost
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.surepay.auth_presentation.login.LoginScreen
import com.surepay.softcard.navigation.Route
import com.surepay.softcard.ui.theme.SoftCardTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SoftCardTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ){
                    NavHost(
                        navController = navController,
                        startDestination = Route.LOGIN
                    ){
                        composable(Route.LOGIN) {
                            LoginScreen(
                                scaffoldState = scaffoldState,
                                navigateToCardsScreen = {
                                navController.navigate(Route.CARDS){


                                }

                            }
                            )
                        }

                        composable(Route.CARDS){
                        }
                    }
                }
            }
        }
    }
}