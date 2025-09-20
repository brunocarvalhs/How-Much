package br.com.brunocarvalhs.howmuch.app.modules.history.components

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material3.Icon
import br.com.brunocarvalhs.howmuch.R
import br.com.brunocarvalhs.howmuch.app.foundation.analytics.trackClick

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
                IconButton(onClick = {
                    onCancelSelection()
                    trackClick(
                        viewId = "btn_cancel_selection",
                        viewName = "Cancel Selection",
                        screenName = "HistoryScreen"
                    )
                }) {
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
                // Select All / Deselect All
                IconButton(onClick = {
                    if (selectedCount == totalItems) {
                        onNotSelectAll()
                        trackClick(
                            viewId = "btn_deselect_all",
                            viewName = "Deselect All",
                            screenName = "HistoryScreen"
                        )
                    } else {
                        onSelectAll()
                        trackClick(
                            viewId = "btn_select_all",
                            viewName = "Select All",
                            screenName = "HistoryScreen"
                        )
                    }
                }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_select_all),
                        contentDescription = stringResource(R.string.select_all),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }

                // Delete Selected
                IconButton(onClick = {
                    onDeleteSelected()
                    trackClick(
                        viewId = "btn_delete_selected",
                        viewName = "Delete Selected",
                        screenName = "HistoryScreen"
                    )
                }, enabled = selectedCount > 0) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(R.string.delete_selected),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            } else {
                // Enter Selection Mode
                IconButton(onClick = {
                    onEnterSelectionMode()
                    trackClick(
                        viewId = "btn_enter_selection_mode",
                        viewName = "Enter Selection Mode",
                        screenName = "HistoryScreen"
                    )()
                }) {
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
        onSelectAll = {},
        onNotSelectAll = {}
    )
}
