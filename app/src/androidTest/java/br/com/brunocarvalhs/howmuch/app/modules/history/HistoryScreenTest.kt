package br.com.brunocarvalhs.howmuch.app.modules.history

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HistoryScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testHistoryScreenDisplaysContent() {
        composeTestRule.setContent {
            HistoryContent(
                uiState = HistoryUiState()
            )
        }

        // Verifica se o filtro "Todos" está presente
        composeTestRule.onNodeWithText("Todos")
            .assertExists()
            .performClick()

        // Verifica se o filtro "Últimos 7 dias" está presente
        composeTestRule.onNodeWithText("Últimos 7 dias")
            .assertExists()
    }
}
