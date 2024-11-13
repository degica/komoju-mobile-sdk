package com.komoju.mobile.sdk.ui.screens.failed

/**
 * Enum class representing the reasons for a payment failure.
 *
 * This enum provides various constants that describe common reasons why a payment may fail.
 * These reasons can be used to handle different error cases in the application and provide
 * appropriate feedback to the user.
 */
enum class Reason {

    /**
     * Payment was canceled by the user.
     *
     * This reason indicates that the user deliberately canceled the payment process.
     * It typically happens when the user decides not to complete the transaction.
     */
    USER_CANCEL,

    /**
     * Payment failed due to a credit card error.
     *
     * This reason indicates that there was an issue with the user's credit card, such as
     * an invalid card number, insufficient funds, or the card being declined by the issuer.
     */
    CREDIT_CARD_ERROR,

    /**
     * Payment failed due to another unspecified reason.
     *
     * This reason is used when the cause of the failure does not fit into any of the other
     * predefined categories.
     */
    OTHER,
}
