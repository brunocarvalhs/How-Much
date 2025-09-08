package br.com.brunocarvalhs.howmuch.app.modules.history.components

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material3.Icon
import br.com.brunocarvalhs.howmuch.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryTopBar(
    @StringRes title: Int? = null,
    selectionMode: Boolean,
    selectedCount: Int,
    totalItems: Int,
    onCancelSelection: () -> Unit = {},
    onEnterSelectionMode: () -> Unit = {},
    onDeleteSelected: () -> Unit = {},
    onSelectAll: () -> Unit = {},
    onNotSelectAll: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = if (selectionMode) stringResource(
                    R.string.selected,
                    selectedCount
                ) else title?.let { stringResource(it) }.orEmpty(),
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        navigationIcon = {
            if (selectionMode) {
                IconButton(onClick = onCancelSelection) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.cancel_select_it),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        },
        actions = {
            if (selectionMode) {
                IconButton(onClick = {
                    if (selectedCount == totalItems) {
                        onNotSelectAll()
                    } else {
                        onSelectAll()
                    }
                }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_select_all),
                        contentDescription = stringResource(R.string.select_all),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }

                IconButton(onClick = onDeleteSelected, enabled = selectedCount > 0) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(R.string.delete_selected),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            } else {
                IconButton(onClick = onEnterSelectionMode) {
                    Icon(
                        painter = painterResource(R.drawable.ic_check_box),
                        contentDescription = stringResource(R.string.select_items),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
@Preview
fun HeaderComponentPreview() {
    HistoryTopBar(
        title = R.string.history,
        selectionMode = true,
        selectedCount = 2,
        totalItems = 5,
        onCancelSelection = {},
        onEnterSelectionMode = {},
        onDeleteSelected = {},
        onSelectAll = {}
    )
}