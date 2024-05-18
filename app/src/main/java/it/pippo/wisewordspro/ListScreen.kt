package it.pippo.wisewordspro

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShowProverbsList(proverbs: List<String>, onclick: (s: String) -> Unit) {

    val filter = rememberSaveable {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Column {
        FilterMe(filter, onclick)
        Spacer(modifier = Modifier.height(15.dp))
        ClickHere (
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            onclick = {
                keyboardController?.hide()
                focusManager.clearFocus()
                onclick(filter.value)
            },
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn {
            items(proverbs) {
                Proverb(
                    s = it,
                    Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 80.dp)
                )
            }
        }
    }
}