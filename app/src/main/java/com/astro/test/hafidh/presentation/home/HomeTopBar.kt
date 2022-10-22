package com.astro.test.hafidh.presentation.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@ExperimentalComposeUiApi
@Composable
fun HomeTopBar(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit,
    closeButtonVisibility: Boolean,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .semantics {
                contentDescription = "SearchWidget"
            },
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.background
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .semantics {
                    contentDescription = "TextField"
                },
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = {
                Text(
                    modifier = Modifier
                        .alpha(alpha = ContentAlpha.medium),
                    text = "Search...",
                    color = Color.LightGray
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.primary
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .alpha(alpha = ContentAlpha.medium),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = MaterialTheme.colors.primary
                    )
                }
            },
            trailingIcon = {
                if (closeButtonVisibility) {
                    IconButton(
                        modifier = Modifier
                            .semantics {
                                contentDescription = "CloseButton"
                            },
                        onClick = {
                            onCloseClicked()
                            keyboardController?.hide()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close Icon",
                            tint = MaterialTheme.colors.primary
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                    keyboardController?.hide()
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.primaryVariant
            )
        )
    }
}

@ExperimentalComposeUiApi
@Composable
@Preview
fun SearchWidgetPreview() {
    HomeTopBar(
        text = "Search",
        onTextChange = {},
        onSearchClicked = {},
        onCloseClicked = {},
        true
    )
}