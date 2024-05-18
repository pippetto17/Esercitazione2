package it.pippo.wisewordspro

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import it.pippo.wisewordspro.ui.theme.boxPadding
import it.pippo.wisewordspro.ui.theme.fontSize
import it.pippo.wisewordspro.ui.theme.iconSize
import it.pippo.wisewordspro.ui.theme.lineHeight
import it.pippo.wisewordspro.ui.theme.proverbsFontSize
import it.pippo.wisewordspro.ui.theme.romaRed
import it.pippo.wisewordspro.ui.theme.romaYellow
import it.pippo.wisewordspro.ui.theme.smallPadding

@Composable
fun fontFamily(): FontFamily {
    val assets = LocalContext.current.assets
    return FontFamily(
        Font("openSans.ttf", assets)
    )
}

@Composable
fun titleFont(): FontFamily {
    val assets = LocalContext.current.assets
    return FontFamily(
        Font("Chomsky.otf", assets)
    )
}

@Composable
fun Proverb(s: String, modifier: Modifier, collapsedMaxLines: Int = 3,
            dialogTitle: String = "Proverbio",
            dialogText: String = s) {

    var expanded by remember { mutableStateOf(false) }
    Text(
        text = s,
        modifier = modifier
            .clickable {expanded = true}
            .padding(smallPadding)
            .border(2.dp, romaYellow, RoundedCornerShape(10.dp))
            .padding(boxPadding),
        textAlign = TextAlign.Center,
        fontSize = proverbsFontSize,
        fontStyle = FontStyle.Italic,
        fontFamily = fontFamily(),
        overflow = TextOverflow.Ellipsis,
        color = Color.White
    )
    if (expanded) {
        AlertDialog(
            onDismissRequest = { expanded = false },
            title = {
                Text(
                    dialogTitle,
                    fontSize = fontSize,
                    fontFamily = fontFamily(),
                    color = romaYellow
                )},
            text = {
                Text(
                    dialogText,
                    fontSize = fontSize,
                    fontFamily = fontFamily(),
                    fontStyle = FontStyle.Italic,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )},
            confirmButton = {},
            containerColor = romaRed,
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FilterMe(filter: MutableState<String>, onclick: (filter: String) -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = filter.value,
        onValueChange = {
            filter.value = it
            onclick(it)
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
            color = Color.White
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
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            keyboardController?.hide()
            focusManager.clearFocus()
            onclick(filter.value)
        }),
        trailingIcon = {
            Icon(
                Icons.Rounded.Search,
                contentDescription = null,
                modifier = Modifier
                    .size(iconSize, iconSize)
                    .clickable {
                        onclick(filter.value)
                        keyboardController?.hide()
                    })
        }
    )
}

@Composable
fun ClickHere(modifier: Modifier = Modifier, onclick: () -> Unit) {
    Button(
        modifier = modifier,
        onClick = onclick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF2C2C2C),
        ),
        shape = RoundedCornerShape(10.dp)

    ) {
        Text(
            text = stringResource(id = R.string.message),
            textAlign = TextAlign.Center,
            fontSize = fontSize,
            fontStyle = FontStyle.Italic,
            color = romaYellow,
            lineHeight = lineHeight,
            fontFamily = fontFamily()
        )
    }
}