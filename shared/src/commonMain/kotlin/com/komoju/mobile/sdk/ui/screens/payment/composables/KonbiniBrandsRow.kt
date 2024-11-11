package com.komoju.mobile.sdk.ui.screens.payment.composables

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
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.komoju.mobile.sdk.entities.PaymentMethod.Konbini.KonbiniBrand
import com.komoju.mobile.sdk.i18n.I18nStringKey
import com.komoju.mobile.sdk.i18n.i18nStringResource
import com.komoju.mobile.sdk.ui.icon.DailyYamazaki
import com.komoju.mobile.sdk.ui.icon.FamilyMart
import com.komoju.mobile.sdk.ui.icon.KomojuIcon
import com.komoju.mobile.sdk.ui.icon.Lawson
import com.komoju.mobile.sdk.ui.icon.Ministop
import com.komoju.mobile.sdk.ui.icon.SeicoMart
import com.komoju.mobile.sdk.ui.icon.SevenEleven
import com.komoju.mobile.sdk.ui.theme.Gray200
import com.komoju.mobile.sdk.ui.theme.KomojuDarkGreen
import com.komoju.mobile.sdk.ui.theme.KomojuMobileSdkTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

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
            painter = rememberVectorPainter(konbiniBrand.displayIcon),
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
        is KonbiniBrand.DailyYamazaki -> i18nStringResource(I18nStringKey.daily_yamazaki)
        is KonbiniBrand.FamilyMart -> i18nStringResource(I18nStringKey.family_mart)
        is KonbiniBrand.Lawson -> i18nStringResource(I18nStringKey.lawson)
        is KonbiniBrand.MiniStop -> i18nStringResource(I18nStringKey.ministop)
        is KonbiniBrand.SeicoMart -> i18nStringResource(I18nStringKey.seicomart)
        is KonbiniBrand.SevenEleven -> i18nStringResource(I18nStringKey._7_eleven)
    }

private val KonbiniBrand.displayIcon
    get() = when (this) {
        is KonbiniBrand.DailyYamazaki -> KomojuIcon.DailyYamazaki
        is KonbiniBrand.FamilyMart -> KomojuIcon.FamilyMart
        is KonbiniBrand.Lawson -> KomojuIcon.Lawson
        is KonbiniBrand.MiniStop -> KomojuIcon.Ministop
        is KonbiniBrand.SeicoMart -> KomojuIcon.SeicoMart
        is KonbiniBrand.SevenEleven -> KomojuIcon.SevenEleven
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
