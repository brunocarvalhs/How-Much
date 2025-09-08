package br.com.brunocarvalhs.howmuch.app.modules.shoppingCart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.brunocarvalhs.howmuch.app.foundation.constants.ONE_INT
import br.com.brunocarvalhs.howmuch.app.foundation.constants.THREE_INT
import br.com.brunocarvalhs.howmuch.app.foundation.constants.TWO_INT
import br.com.brunocarvalhs.howmuch.app.foundation.constants.ZERO_INT
import br.com.brunocarvalhs.howmuch.app.modules.shoppingCart.ShoppingCartUiState

@Composable
fun ShoppingCartCardsPager(
    uiState: ShoppingCartUiState,
    onCheckout: () -> Unit = {},
    onShared: () -> Unit = {}
) {
    val pagerState = rememberPagerState(
        initialPage = ZERO_INT,
        pageCount = { THREE_INT }
    )

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .background(MaterialTheme.colorScheme.primary),
    ) { page ->
        when (page) {
            ZERO_INT -> ShoppingCartInfo(
                totalPrice = uiState.totalPrice,
                productsCount = uiState.products.size,
                isLoading = uiState.isLoading,
                onCheckout = onCheckout,
                onShared = onShared,
                modifier = Modifier.fillMaxWidth()
            )

            ONE_INT -> ShoppingCartLimitCard(
                totalSpent = uiState.totalPrice,
                modifier = Modifier.fillMaxWidth()
            )

            TWO_INT -> ShoppingCartSavingsCard(
                amountSaved = uiState.amountSaved,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
