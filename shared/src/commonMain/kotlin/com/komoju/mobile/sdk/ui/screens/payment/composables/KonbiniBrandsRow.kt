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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.komoju.mobile.sdk.entities.PaymentMethod.Konbini.KonbiniBrand
import komoju_mobile_sdk.shared.generated.resources.Res
import com.komoju.mobile.sdk.ui.theme.Gray200
import com.komoju.mobile.sdk.ui.theme.KomojuDarkGreen
import com.komoju.mobile.sdk.ui.theme.KomojuMobileSdkTheme
import komoju_mobile_sdk.shared.generated.resources.komoju__7_eleven
import komoju_mobile_sdk.shared.generated.resources.komoju_daily_yamazaki
import komoju_mobile_sdk.shared.generated.resources.komoju_family_mart
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_daily_yamazaki
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_family_mart
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_lawson
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_ministop
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_seico_mart
import komoju_mobile_sdk.shared.generated.resources.komoju_ic_seven_eleven
import komoju_mobile_sdk.shared.generated.resources.komoju_lawson
import komoju_mobile_sdk.shared.generated.resources.komoju_ministop
import komoju_mobile_sdk.shared.generated.resources.komoju_seicomart
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
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
        is KonbiniBrand.DailyYamazaki -> stringResource(Res.string.komoju_daily_yamazaki)
        is KonbiniBrand.FamilyMart -> stringResource(Res.string.komoju_family_mart)
        is KonbiniBrand.Lawson -> stringResource(Res.string.komoju_lawson)
        is KonbiniBrand.MiniStop -> stringResource(Res.string.komoju_ministop)
        is KonbiniBrand.SeicoMart -> stringResource(Res.string.komoju_seicomart)
        is KonbiniBrand.SevenEleven -> stringResource(Res.string.komoju__7_eleven)
    }

private val KonbiniBrand.displayIcon
    get() = when (this) {
        is KonbiniBrand.DailyYamazaki -> Res.drawable.komoju_ic_daily_yamazaki
        is KonbiniBrand.FamilyMart -> Res.drawable.komoju_ic_family_mart
        is KonbiniBrand.Lawson -> Res.drawable.komoju_ic_lawson
        is KonbiniBrand.MiniStop -> Res.drawable.komoju_ic_ministop
        is KonbiniBrand.SeicoMart -> Res.drawable.komoju_ic_seico_mart
        is KonbiniBrand.SevenEleven -> Res.drawable.komoju_ic_seven_eleven
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
