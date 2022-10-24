package com.surepay.softcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.surepay.auth_presentation.login.LoginScreen
import com.surepay.cards_presentation.card_details.CardDetailsScreen
import com.surepay.cards_presentation.CardsScreen
import com.surepay.cards_presentation.pin_verification.PinScreen
import com.surepay.cards_presentation.pin_verification.PinVerificationScreen
import com.surepay.core_ui.theme.SoftCardTheme
import com.surepay.softcard.navigation.Route
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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
                        startDestination = Route.CARDS
                    ){
                        composable(Route.LOGIN) {
                            LoginScreen(
                                scaffoldState = scaffoldState,
                                navigateToCardsScreen = {
                                navController.navigate(Route.CARDS)
                            }
                            )
                        }

                        composable(Route.CARDS){
                            CardsScreen(
                                scaffoldState = scaffoldState,
                                onNavigateCardDetails = { cardHolderName, cardExpirationDate, cardNumber, cardLogo ->
                                    navController.navigate(
                                        Route.CARDS_DETAILS +
                                                "/$cardHolderName" +
                                                "/$cardExpirationDate" +
                                                "/$cardNumber" +
                                                "/$cardLogo"
                                    )
                                }
                            )
                        }

                        composable(
                            route = Route.CARDS_DETAILS + "/{cardHolderName}/{cardExpirationDate}/{cardNumber}/{cardLogo}",
                            arguments = listOf(
                                navArgument("cardHolderName"){
                                    type = NavType.StringType
                                },
                                navArgument("cardExpirationDate"){
                                    type = NavType.StringType
                                },
                                navArgument("cardNumber"){
                                    type = NavType.StringType
                                },
                                navArgument("cardLogo"){
                                    type = NavType.IntType
                                },
                            )
                        ){
                            val cardHolderName = it.arguments?.getString("cardHolderName")!!
                            val cardExpirationDate = it.arguments?.getString("cardExpirationDate")!!
                            val cardNumber = it.arguments?.getString("cardNumber")!!
                            val cardLogo = it.arguments?.getInt("cardLogo")!!

                            CardDetailsScreen(
                                scaffoldState = scaffoldState,
                                cardHolderName =cardHolderName,
                                cardExpirationDate = cardExpirationDate,
                                cardNumber = cardNumber,
                                cardLogo = cardLogo,
                                onNavigateUp = {
                                    navController.navigateUp()
                                },
                                onNavigatePinCode = {
                                    navController.navigate(Route.PIN_CODE)
                                }
                            )
                        }

                        composable(Route.PIN_CODE) {
                            PinScreen(
                                scaffoldState = scaffoldState,
                                onNavigateUp = {
                                    navController.navigateUp()
                                },
                               onNavigatePinVerification = {
                                   navController.navigate(Route.PIN_VERIFICATION)
                               }
                            )
                        }

                        composable(Route.PIN_VERIFICATION){
                            PinVerificationScreen(scaffoldState = scaffoldState,
                            onNavigateUp = {
                                navController.navigateUp()
                            })
                        }
                    }
                }
            }
        }
    }
}