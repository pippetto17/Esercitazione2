package it.pippo.wisewordspro

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import it.pippo.wisewordspro.ui.theme.fontSize
import it.pippo.wisewordspro.ui.theme.iconSize
import it.pippo.wisewordspro.ui.theme.lineHeight
import it.pippo.wisewordspro.ui.theme.romaYellow
import it.pippo.wisewordspro.ui.theme.smallPadding
import it.pippo.wisewordspro.ui.theme.titleSize

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShowRandomProverbs(text: String, onclick: (filter: String) -> Unit) {
    var filter by rememberSaveable {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(50.dp))
            Text(text = stringResource(id = R.string.title),
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = titleSize,
                fontStyle = FontStyle.Italic,
                color = romaYellow,
                lineHeight = lineHeight,
                fontFamily = titleFont(),
            )
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.size(50.dp))
            OutlinedTextField(
                value = filter,
                onValueChange = {
                    filter = it
                },
                shape = RoundedCornerShape(30.dp),
                label = {
                    Text(
                        text = "Filter",
                        fontSize = fontSize,
                        fontFamily = fontFamily(),
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold

                    )
                },
                textStyle = TextStyle(
                    fontSize = fontSize,
                    fontFamily = fontFamily(),
                    color= Color.White
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedLabelColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    unfocusedTrailingIconColor = Color.White,

                    focusedLabelColor = romaYellow,
                    focusedBorderColor = romaYellow,
                    focusedTrailingIconColor = romaYellow,
                    cursorColor = romaYellow,
                ),
                modifier = Modifier
                    .padding(smallPadding)
                    .fillMaxWidth(),
                trailingIcon = {
                    Icon(Icons.Rounded.Search,
                        contentDescription = null,
                        modifier = Modifier
                            .size(iconSize, iconSize)
                            .clickable {
                                onclick(filter)
                                keyboardController?.hide()
                            })
                }
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(smallPadding)
            .defaultMinSize(minHeight = 200.dp)
            .border(width = 4.dp, color = romaYellow, shape = RoundedCornerShape(30.dp))
            .clickable {
                keyboardController?.hide()
                focusManager.clearFocus()
                onclick(filter)
            },
            contentAlignment = Alignment.Center
        ) {
            Text(
                color = Color.White,
                text = if (text == "") {
                    stringResource(id = R.string.message)
                } else {
                    text
                },
                textAlign = TextAlign.Center,
                fontSize = fontSize,
                fontFamily = fontFamily()
            )
        }
    }
}