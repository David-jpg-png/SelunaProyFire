package me.andresdavid.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.andresdavid.myapplication.ui.screen.CalendarScreen
import me.andresdavid.myapplication.ui.screen.DailyLogScreen
import me.andresdavid.myapplication.ui.screen.HomeScreen
import me.andresdavid.myapplication.ui.screen.InsightsScreen
import me.andresdavid.myapplication.ui.screen.LoginScreen
import me.andresdavid.myapplication.ui.screen.NaturalRhythmScreen
import me.andresdavid.myapplication.ui.screen.PersonalizationScreen
import me.andresdavid.myapplication.ui.screen.RegisterScreen

object Screen {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val NATURAL_RHYTHM = "natural_rhythm"
    const val PERSONALIZATION = "personalization"
    const val HOME = "home"
    const val CALENDAR = "calendar"
    const val INSIGHTS = "insights"
    const val DAILY_LOG = "daily_log"
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.LOGIN
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.LOGIN) {
            LoginScreen(
                onNavigateToHome = {
                    navController.navigate(Screen.HOME) {
                        popUpTo(Screen.LOGIN) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(Screen.REGISTER)
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.REGISTER) {
            RegisterScreen(
                onNavigateToLogin = {
                    navController.navigate(Screen.LOGIN) {
                        popUpTo(Screen.REGISTER) { inclusive = true }
                    }
                },
                onNavigateToNaturalRhythm = {
                    navController.navigate(Screen.NATURAL_RHYTHM)
                }
            )
        }

        composable(Screen.NATURAL_RHYTHM) {
            NaturalRhythmScreen(
                onNavigateToPersonalization = {
                    navController.navigate(Screen.PERSONALIZATION)
                },
                onNavigateToHome = {
                    navController.navigate(Screen.HOME) {
                        popUpTo(Screen.LOGIN) { inclusive = true }
                    }
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.PERSONALIZATION) {
            PersonalizationScreen(
                onNavigateToHome = {
                    navController.navigate(Screen.HOME) {
                        popUpTo(Screen.LOGIN) { inclusive = true }
                    }
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.HOME) {
            HomeScreen(
                onNavigateToTab = { tabIndex ->
                    val destination = when (tabIndex) {
                        0 -> Screen.HOME
                        1 -> Screen.CALENDAR
                        2 -> Screen.INSIGHTS
                        3 -> Screen.DAILY_LOG
                        else -> Screen.HOME
                    }
                    if (destination != Screen.HOME) {
                        navController.navigate(destination) {
                            launchSingleTop = true
                            popUpTo(Screen.HOME)
                        }
                    }
                },
                onNavigateToDailyLog = {
                    navController.navigate(Screen.DAILY_LOG) {
                        launchSingleTop = true
                        popUpTo(Screen.HOME)
                    }
                }
            )
        }

        composable(Screen.CALENDAR) {
            CalendarScreen(
                onNavigateToTab = { tabIndex ->
                    val destination = when (tabIndex) {
                        0 -> Screen.HOME
                        1 -> Screen.CALENDAR
                        2 -> Screen.INSIGHTS
                        3 -> Screen.DAILY_LOG
                        else -> Screen.CALENDAR
                    }
                    if (destination != Screen.CALENDAR) {
                        navController.navigate(destination) {
                            launchSingleTop = true
                            popUpTo(Screen.HOME)
                        }
                    }
                },
                onNavigateToDailyLog = {
                    navController.navigate(Screen.DAILY_LOG) {
                        launchSingleTop = true
                        popUpTo(Screen.HOME)
                    }
                }
            )
        }

        composable(Screen.INSIGHTS) {
            InsightsScreen(
                onNavigateToTab = { tabIndex ->
                    val destination = when (tabIndex) {
                        0 -> Screen.HOME
                        1 -> Screen.CALENDAR
                        2 -> Screen.INSIGHTS
                        3 -> Screen.DAILY_LOG
                        else -> Screen.INSIGHTS
                    }
                    if (destination != Screen.INSIGHTS) {
                        navController.navigate(destination) {
                            launchSingleTop = true
                            popUpTo(Screen.HOME)
                        }
                    }
                }
            )
        }

        composable(Screen.DAILY_LOG) {
            DailyLogScreen(
                onNavigateToTab = { tabIndex ->
                    val destination = when (tabIndex) {
                        0 -> Screen.HOME
                        1 -> Screen.CALENDAR
                        2 -> Screen.INSIGHTS
                        3 -> Screen.DAILY_LOG
                        else -> Screen.DAILY_LOG
                    }
                    if (destination != Screen.DAILY_LOG) {
                        navController.navigate(destination) {
                            launchSingleTop = true
                            popUpTo(Screen.HOME)
                        }
                    }
                },
                onSaveLog = {
                    navController.navigate(Screen.HOME) {
                        launchSingleTop = true
                        popUpTo(Screen.HOME)
                    }
                }
            )
        }
    }
}
