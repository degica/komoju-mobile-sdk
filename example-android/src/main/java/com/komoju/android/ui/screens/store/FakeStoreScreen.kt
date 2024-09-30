package com.komoju.android.ui.screens.store

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberNavigatorScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.komoju.android.R
import com.komoju.android.ui.theme.KomojuDarkGreen
import com.komoju.android.ui.theme.KomojuFakeStoreTheme
import com.komoju.android.ui.theme.KomojuLightGreen

class FakeStoreScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = navigator.rememberNavigatorScreenModel { FakeStoreScreenModel() }
        val uiState by screenModel.uiState.collectAsState()
        val context = LocalContext.current
        LaunchedEffect(uiState.error) {
            uiState.error?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                screenModel.onErrorShown()
            }
        }

        if (uiState.items.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    buildAnnotatedString {
                        append(stringResource(R.string.sound))
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold, color = KomojuLightGreen)) {
                            append("${stringResource(R.string.bud)}!")
                        }
                    },
                    modifier = Modifier.padding(horizontal = 16.dp),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal,
                )
                Text(
                    stringResource(R.string.description),
                    fontSize = 12.sp,
                    color = KomojuDarkGreen,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
                LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(8.dp)) {
                    itemsIndexed(uiState.items) { index, item ->
                        Item(
                            item = item,
                            onItemChanged = { newItem ->
                                screenModel.onItemChanged(newItem)
                            },
                        ) {
                            navigator.push(FakeItemDetailScreen(index))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Item(item: Item, onItemChanged: (Item) -> Unit = {}, onItemClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onItemClicked),
    ) {
        Box {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.77f),
                painter = painterResource(id = item.imageID),
                contentDescription = "",
                contentScale = ContentScale.Inside,
            )
            Icon(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(CircleShape)
                    .clickable {
                        onItemChanged(item.copy(isFavorite = item.isFavorite.not()))
                    }
                    .background(Color.LightGray.copy(alpha = .2f), CircleShape)
                    .padding(12.dp)
                    .align(Alignment.TopEnd),
                imageVector = if (item.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "",
                tint = if (item.isFavorite) Color.Red else Color.DarkGray,
            )
        }
        Text("¥" + item.price + ".00", fontSize = 18.sp, color = Color.Black, fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp))
        Text(stringResource(item.name), fontSize = 14.sp, color = Color.Black, fontWeight = FontWeight.Medium, lineHeight = 16.sp, modifier = Modifier.padding(horizontal = 4.dp))
        Text(
            stringResource(R.string.model) + ": " + item.model + ", " + stringResource(item.color),
            fontSize = 12.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(horizontal = 4.dp),
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun ItemPreview() {
    var item by remember {
        mutableStateOf(
            Item(
                imageID = R.drawable.headphone_1,
                name = R.string.headphone_name,
                description = R.string.headphone_description,
                price = "199",
                model = "HD1000XMG",
                color = R.string.gold,
            ),
        )
    }
    KomojuFakeStoreTheme {
        Item(
            item,
            {
                item = it
            },
        ) {
        }
    }
}
