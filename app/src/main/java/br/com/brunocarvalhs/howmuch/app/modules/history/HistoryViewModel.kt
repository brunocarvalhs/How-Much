package br.com.brunocarvalhs.howmuch.app.modules.history

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.brunocarvalhs.domain.entities.ShoppingCart
import br.com.brunocarvalhs.domain.useCases.DeleteCartOfHistoryUseCase
import br.com.brunocarvalhs.domain.useCases.GetHistoryCartUseCase
import br.com.brunocarvalhs.howmuch.app.foundation.extensions.DateFormat
import br.com.brunocarvalhs.howmuch.app.foundation.extensions.shareText
import br.com.brunocarvalhs.howmuch.app.foundation.extensions.toCurrencyString
import br.com.brunocarvalhs.howmuch.app.foundation.extensions.toFormatDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getHistoryUseCase: GetHistoryCartUseCase,
    private val deleteCartOfHistoryUseCase: DeleteCartOfHistoryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HistoryUiState())
    val uiState: StateFlow<HistoryUiState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<HistoryUiEffect>()
    val uiEffect: SharedFlow<HistoryUiEffect> = _uiEffect.asSharedFlow()

    fun onIntent(intent: HistoryUiIntent) {
        when (intent) {
            HistoryUiIntent.Retry -> loadHistory()
            HistoryUiIntent.ClearHistory -> clearHistory()
            is HistoryUiIntent.DeleteSelected -> deleteSelected(intent.list)

        }
    }

    private fun deleteSelected(list: List<ShoppingCart>) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteCartOfHistoryUseCase(list)
                .onSuccess {
                    val currentHistory = _uiState.value.historyItems.toMutableList()
                    currentHistory.removeAll(list)
                    _uiState.value = _uiState.value.copy(historyItems = currentHistory)
                }.onFailure {
                    _uiEffect.emit(HistoryUiEffect.ShowError(it.message.orEmpty()))
                }
        }
    }

    private fun loadHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            getHistoryUseCase()
                .onSuccess { history ->
                    _uiState.value = _uiState.value.copy(historyItems = history)
                }.onFailure {
                    _uiEffect.emit(HistoryUiEffect.ShowError(it.message.orEmpty()))
                }
        }
    }

    private fun clearHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteCartOfHistoryUseCase(_uiState.value.historyItems)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(historyItems = emptyList())
                }.onFailure {
                    _uiEffect.emit(HistoryUiEffect.ShowError(it.message.orEmpty()))
                }
        }
    }

    fun sharedCart(context: Context, cart: ShoppingCart) {
        val shareText = StringBuilder().apply {
            append("Shopping Cart: ${cart.market}\n")
            append("Date: ${cart.purchaseDate?.toFormatDate(DateFormat.DAY_MONTH_YEAR)}\n")
            append("Total: R$ ${cart.totalPrice.toCurrencyString()}\n")
            append("\nItems:\n")
            cart.products.forEach { item ->
                append("- ${item.name}: ${item.quantity} x R$ ${item.price.toCurrencyString()} = R$ ${(item.quantity * item.price).toCurrencyString()}\n")
            }
        }.toString()
        context.shareText(shareText, "Shopping Cart from ${cart.market}")
    }
}
