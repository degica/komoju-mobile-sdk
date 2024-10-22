package com.komoju.android.sdk.ui.screens.payment.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.komoju.android.sdk.R
import com.komoju.android.sdk.ui.theme.Gray200
import com.komoju.android.sdk.ui.theme.KomojuDarkGreen
import com.komoju.android.sdk.ui.theme.KomojuMobileSdkTheme
import com.komoju.mobile.sdk.entities.PaymentMethod.Konbini.KonbiniBrand

@Composable
internal fun KonbiniBrandsRow(konbiniBrands: List<KonbiniBrand>, selectedKonbiniBrand: KonbiniBrand?, onSelected: (KonbiniBrand) -> Unit) {
    LazyRow(contentPadding = PaddingValues(horizontal = 12.dp)) {
        items(konbiniBrands) { konbiniBrand ->
            KonbiniBrand(
                konbiniBrand,
                konbiniBrand == selectedKonbiniBrand,
                onSelected = {
                    onSelected(konbiniBrand)
                },
            )
        }
    }
}

@Composable
private fun KonbiniBrand(konbiniBrand: KonbiniBrand, isSelected: Boolean, onSelected: () -> Unit) {
    Column(
        modifier = Modifier
            .defaultMinSize(minWidth = 120.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, if (isSelected) KomojuDarkGreen else Gray200, RoundedCornerShape(8.dp))
            .clickable(onClick = onSelected)
            .padding(8.dp),
    ) {
        Image(
            painter = painterResource(konbiniBrand.displayIcon),
            contentDescription = "${konbiniBrand.key} icon",
            modifier = Modifier.size(32.dp),
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(konbiniBrand.displayText, fontSize = 14.sp)
    }
}

private val KonbiniBrand.displayText
    @Composable
    get() = when (this) {
        is KonbiniBrand.DailyYamazaki -> stringResource(R.string.komoju_daily_yamazaki)
        is KonbiniBrand.FamilyMart -> stringResource(R.string.komoju_family_mart)
        is KonbiniBrand.Lawson -> stringResource(R.string.komoju_lawson)
        is KonbiniBrand.MiniStop -> stringResource(R.string.komoju_ministop)
        is KonbiniBrand.SeicoMart -> stringResource(R.string.komoju_seicomart)
        is KonbiniBrand.SevenEleven -> stringResource(R.string.komoju__7_eleven)
    }

private val KonbiniBrand.displayIcon
    get() = when (this) {
        is KonbiniBrand.DailyYamazaki -> R.drawable.komoju_ic_daily_yamazaki
        is KonbiniBrand.FamilyMart -> R.drawable.komoju_ic_family_mart
        is KonbiniBrand.Lawson -> R.drawable.komoju_ic_lawson
        is KonbiniBrand.MiniStop -> R.drawable.komoju_ic_ministop
        is KonbiniBrand.SeicoMart -> R.drawable.komoju_ic_seico_mart
        is KonbiniBrand.SevenEleven -> R.drawable.komoju_ic_seven_eleven
    }

@Composable
@Preview
private fun KonbiniRowPreview() {
    val konbiniList = listOf(
        KonbiniBrand.SevenEleven(
            key = "seven-eleven",
            merchantNumber = "",
        ),
        KonbiniBrand.Lawson(key = "lawson"),
        KonbiniBrand.FamilyMart(key = "family-mart"),
        KonbiniBrand.MiniStop(key = "ministop"),
        KonbiniBrand.DailyYamazaki(key = "daily-yamazaki"),
        KonbiniBrand.SeicoMart(key = "seicomart"),
    )
    KomojuMobileSdkTheme {
        KonbiniBrandsRow(konbiniList, konbiniList.first()) { }
    }
}
