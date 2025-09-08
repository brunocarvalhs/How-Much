package br.com.brunocarvalhs.howmuch.app.modules.history.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.brunocarvalhs.howmuch.R

@Composable
fun HistoryFilter(
    selectedFilter: HistoryFilterType,
    onFilterSelected: (HistoryFilterType) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(HistoryFilterType.entries) { filter ->
            FilterChip(
                selected = selectedFilter == filter,
                onClick = { onFilterSelected(filter) },
                label = { Text(stringResource(filter.displayName)) }
            )
        }
    }
}

enum class HistoryFilterType(@param:StringRes val displayName: Int) {
    ALL(R.string.all),
    TODAY(R.string.today),
    LAST_SEVEN_DAYS(R.string.last_7_days),
    LAST_THIRTY_DAYS(R.string.last_30_days),
    CURRENT_MONTH(R.string.current_month)
}