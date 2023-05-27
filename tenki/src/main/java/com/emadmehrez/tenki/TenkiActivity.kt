package com.emadmehrez.tenki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.emadmehrez.tenki.ui.screen.TenkiApp
import com.emadmehrez.tenki.ui.screen.dailyforecast.DailyForecastUiState
import com.emadmehrez.tenki.ui.screen.dailyforecast.DailyForecastViewModel
import com.emadmehrez.tenki.ui.theme.TenkiTheme
import com.google.accompanist.adaptive.calculateDisplayFeatures
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TenkiActivity : ComponentActivity() {
    private val dailyForecastViewModel: DailyForecastViewModel by viewModels()
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TenkiTheme {
                val windowSizeClass = calculateWindowSizeClass(activity = this)
                val displayFeatures = calculateDisplayFeatures(activity = this)
                val dailyForecastUiState by dailyForecastViewModel.state.collectAsStateWithLifecycle()
                TenkiApp(
                    widthSizeClass = windowSizeClass.widthSizeClass,
                    displayFeatures = displayFeatures,
                    dailyForecastUiState = dailyForecastUiState,
                    closeDetailsScreen = dailyForecastViewModel::closeDetailsScreen,
                    navigateToDetailsScreen = dailyForecastViewModel::navigateToDetailsScreen
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, widthDp = 400, heightDp = 900)
@Composable
fun TenkiAppPreviewPhone() {
    TenkiTheme {
        val windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(400.dp, 900.dp))
        TenkiApp(
            widthSizeClass = windowSizeClass.widthSizeClass,
            displayFeatures = emptyList(),
            dailyForecastUiState = DailyForecastUiState.Success()
        )
    }
}
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, widthDp = 700, heightDp = 500)
@Composable
fun TenkiAppPreviewTablet() {
    TenkiTheme {
        val windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(700.dp, 500.dp))
        TenkiApp(
            widthSizeClass = windowSizeClass.widthSizeClass,
            displayFeatures = emptyList(),
            dailyForecastUiState = DailyForecastUiState.Success()
        )
    }
}
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, widthDp = 500, heightDp = 700)
@Composable
fun TenkiAppPreviewTabletPortrait() {
    TenkiTheme {
        val windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(500.dp, 700.dp))
        TenkiApp(
            widthSizeClass = windowSizeClass.widthSizeClass,
            displayFeatures = emptyList(),
            dailyForecastUiState = DailyForecastUiState.Success()
        )
    }
}
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, widthDp = 1100, heightDp = 600)
@Composable
fun TenkiAppPreviewDesktop() {
    TenkiTheme {
        val windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(1100.dp, 600.dp))
        TenkiApp(
            widthSizeClass = windowSizeClass.widthSizeClass,
            displayFeatures = emptyList(),
            dailyForecastUiState = DailyForecastUiState.Success()
        )
    }
}
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, widthDp = 600, heightDp = 1100)
@Composable
fun TenkiAppPreviewDesktopPortrait() {
    TenkiTheme {
        val windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(600.dp, 1100.dp))
        TenkiApp(
            widthSizeClass = windowSizeClass.widthSizeClass,
            displayFeatures = emptyList(),
            dailyForecastUiState = DailyForecastUiState.Success()
        )
    }
}
