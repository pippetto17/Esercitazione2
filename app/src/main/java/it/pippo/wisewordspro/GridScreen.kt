package it.pippo.wisewordspro

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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
fun ShowProverbsGrid(proverbs: List<String>, onclick: (s: String) -> Unit) {

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
        LazyHorizontalGrid(rows = GridCells.Fixed(3)) {
            itemsIndexed(proverbs) { _, it ->
                Proverb(
                    s = it,
                    Modifier
                        .width(200.dp)
                        .defaultMinSize(minHeight = 80.dp),
                )
            }
        }
    }
}