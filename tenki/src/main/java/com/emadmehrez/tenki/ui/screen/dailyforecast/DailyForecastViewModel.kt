package com.emadmehrez.tenki.ui.screen.dailyforecast

import androidx.lifecycle.ViewModel
import com.emadmehrez.tenki.ui.util.TenkiContentType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DailyForecastViewModel @Inject constructor(

) : ViewModel() {
    private val _state = MutableStateFlow(DailyForecastUiState.Success())
    val state = _state.asStateFlow()

    fun closeDetailsScreen() {

    }

    fun navigateToDetailsScreen(id: Long, contentType: TenkiContentType) {

    }

}
sealed class DailyForecastUiState {
    data class Success(
        val isDetailsScreenOpen: Boolean = false
    ) : DailyForecastUiState()
    data class Loading(val message: String) : DailyForecastUiState()
    data class Error(val type: String, val message: String) : DailyForecastUiState()
}