package com.degica.komoju.mobile.sdk.remote.mappers

import com.degica.komoju.mobile.sdk.entities.Payment
import com.degica.komoju.mobile.sdk.entities.PaymentRequest
import com.degica.komoju.mobile.sdk.remote.dtos.PaymentErrorResponseDto

internal object PaymentErrorMapper {
    fun map(request: PaymentRequest, response: PaymentErrorResponseDto): Payment = Payment.Error(
        response.error?.code.orEmpty(),
        response.error?.message.orEmpty(),
        amount = request.paymentMethod.amount,
        currency = request.paymentMethod.currency,
    )
}
