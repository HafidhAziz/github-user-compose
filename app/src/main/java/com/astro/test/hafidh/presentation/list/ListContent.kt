package com.astro.test.hafidh.presentation.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.astro.test.hafidh.data.model.UserResponseData

@ExperimentalCoilApi
@Composable
fun ListContent(items: LazyPagingItems<UserResponseData>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = items,
            key = { user ->
                user.id ?: (10000000..100000000).random()
            }
        ) { user ->
            user?.let { UserItem(user = it) }
        }
        items.apply {
            item {
                when {
                    loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {
                        LoadingState(
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                    loadState.refresh is LoadState.Error || loadState.append is LoadState.Error -> {

                    }
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun UserItem(user: UserResponseData) {
    val painter = rememberImagePainter(data = user.avatar_url) {
        crossfade(durationMillis = 1000)
    }
    Box(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .alpha(ContentAlpha.medium),
            color = Color.White
        ) {}
        Row(
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .padding(all = 12.dp)
        ) {
            Image(
                modifier = Modifier.width(56.dp).fillMaxHeight(),
                painter = painter,
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 12.dp),
            ) {
                Text(
                    text = user.login.orEmpty(),
                    color = Color.Black,
                    fontSize = MaterialTheme.typography.body1.fontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(
                    modifier = Modifier.height(6.dp)
                )
                Text(
                    text = buildAnnotatedString {
                        append("Score: ")
                        append(user.score.toString())
                    },
                    color = Color.Black,
                    fontSize = MaterialTheme.typography.caption.fontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
@Preview
fun ListContentPreview() {
    UserItem(
        user = UserResponseData(
            id = 1,
            login = "Bedul",
            avatar_url = "",
            score = 10f
        )
    )
}

@Composable
private fun EmptyState() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Not Found",
            color = Color.Black,
            fontSize = MaterialTheme.typography.h5.fontSize
        )
    }
}

@Composable
private fun LoadingState(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Loading...",
            color = Color.Black,
            fontSize = MaterialTheme.typography.h5.fontSize
        )
    }
}