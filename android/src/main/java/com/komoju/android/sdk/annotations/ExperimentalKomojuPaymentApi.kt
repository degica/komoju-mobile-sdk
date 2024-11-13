package com.komoju.android.sdk.annotations

/**
 * Annotation used to mark APIs in the Komoju SDK as experimental.
 * This indicates that the annotated feature or API is subject to change in future releases.
 * Users should be cautious and thoroughly test their code after updating the SDK version.
 *
 * - The annotation requires explicit opt-in to use the marked elements.
 * - A warning will be shown when using experimental APIs.
 *
 * @see [RequiresOptIn] to learn more about Kotlin's opt-in mechanism.
 */
@RequiresOptIn(level = RequiresOptIn.Level.WARNING)
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.CLASS, // Can annotate classes
    AnnotationTarget.ANNOTATION_CLASS, // Can annotate other annotations
    AnnotationTarget.PROPERTY, // Can annotate properties
    AnnotationTarget.FIELD, // Can annotate fields
    AnnotationTarget.LOCAL_VARIABLE, // Can annotate local variables
    AnnotationTarget.VALUE_PARAMETER, // Can annotate function parameters
    AnnotationTarget.CONSTRUCTOR, // Can annotate constructors
    AnnotationTarget.FUNCTION, // Can annotate functions
    AnnotationTarget.PROPERTY_GETTER, // Can annotate property getters
    AnnotationTarget.PROPERTY_SETTER, // Can annotate property setters
    AnnotationTarget.TYPEALIAS, // Can annotate type aliases
)
annotation class ExperimentalKomojuPaymentApi
