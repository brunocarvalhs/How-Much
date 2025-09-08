package br.com.brunocarvalhs.howmuch.app.foundation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector
import br.com.brunocarvalhs.howmuch.R
import br.com.brunocarvalhs.howmuch.app.foundation.theme.AppIcons

enum class NavBarItem(
    @param:DrawableRes val icon: Int,
    val route: String,
    @param:StringRes val label: Int
) {
    HOME(
        icon = R.drawable.ic_garden_cart,
        route = "home",
        label = R.string.nav_bar_home
    ),
    HISTORY(
        icon = R.drawable.ic_history,
        route = "history",
        label = R.string.nav_bar_history
    ),
    MENU(
        icon = R.drawable.ic_apps,
        route = "menu",
        label = R.string.nav_bar_menu
    )
}