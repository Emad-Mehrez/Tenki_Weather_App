package com.emadmehrez.tenki.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.window.layout.DisplayFeature
import androidx.window.layout.FoldingFeature
import com.emadmehrez.tenki.ui.navigation.TenkiNavHost
import com.emadmehrez.tenki.ui.screen.dailyforecast.DailyForecastUiState
import com.emadmehrez.tenki.ui.util.DevicePosture
import com.emadmehrez.tenki.ui.util.TenkiContentType
import com.emadmehrez.tenki.ui.util.isBookPosture
import com.emadmehrez.tenki.ui.util.isSeparating

@Composable
fun TenkiApp(
    modifier: Modifier = Modifier,
    widthSizeClass: WindowWidthSizeClass,
    displayFeatures: List<DisplayFeature>,
    dailyForecastUiState: DailyForecastUiState,
    closeDetailsScreen: () -> Unit = {},
    navigateToDetailsScreen: (Long, TenkiContentType) -> Unit = {_,_->},
) {
    val contentType: TenkiContentType
    val foldingFeature = displayFeatures.filterIsInstance<FoldingFeature>().firstOrNull()
    val foldingDevicePosture = when {
        isBookPosture(foldingFeature) -> DevicePosture.BookPosture(foldingFeature.bounds)
        isSeparating(foldingFeature) -> DevicePosture.Separating(foldingFeature.bounds, foldingFeature.orientation)
        else -> DevicePosture.NormalPosture
    }
    when(widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            contentType = TenkiContentType.SinglePane
        }
        WindowWidthSizeClass.Medium -> {
            contentType = if(foldingDevicePosture is DevicePosture.BookPosture ||
                    foldingDevicePosture is DevicePosture.Separating) {
                TenkiContentType.DualPane
            } else {
                TenkiContentType.SinglePane
            }
        }
        WindowWidthSizeClass.Expanded -> {
            contentType = TenkiContentType.DualPane
        }
        else -> {
            contentType = TenkiContentType.SinglePane
        }
    }
    TenkiNavHost(
        contentType = contentType,
        displayFeatures = displayFeatures,
        dailyForecastUiState = dailyForecastUiState,
        closeDetailsScreen = closeDetailsScreen,
        navigateToDetailsScreen = navigateToDetailsScreen,
        modifier = modifier.fillMaxSize()
    )
}