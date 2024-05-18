package it.pippo.wisewordspro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import it.pippo.wisewordspro.ui.theme.proverbsFontSize
import it.pippo.wisewordspro.ui.theme.romaDarkerRed
import it.pippo.wisewordspro.ui.theme.romaRed

@Composable
fun SimpleList(proverbs: List<String>, onclick: (filter: String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        ClickHere (
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            onclick = {
                onclick("")
            },
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn {
            itemsIndexed(proverbs) { index, proverb ->
                Text(
                    text = proverb,
                    modifier= Modifier
                    .fillMaxWidth()
                    .background(
                        if (index % 2 == 0)
                            romaRed
                        else
                            romaDarkerRed
                    ),
                    color = Color.White,
                    fontSize = proverbsFontSize,
                    fontStyle = FontStyle.Italic,

                )
            }
        }
    }
}