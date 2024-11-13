import Foundation
import komojuShared
import SwiftUI

public class KomojuIosSDK {
    public struct Configuration {
        let language: Language
        let currency: Currency
        let publishableKey: String
        let isDebugMode: Bool
        let sessionId: String
        let redirectUrl: String
        let appScheme: String
        let configurableTheme: ConfigurableTheme
        let inlinedProcessing: Bool

        public class Builder {
            private var publishableKey: String
            private var sessionId: String
            private var language: Language = .Japanese // Default language is Japanese.
            private var currency: Currency = .JPY // Default currency is Japanese Yen.
            private var isDebugMode: Bool = false // Debug mode is off by default.
            private var configurableTheme: ConfigurableTheme = ConfigurableThemeCompanion().default_.toIosTheme()
            private var inlinedProcessing: Bool = false
            public init(publishableKey: String, sessionId: String) {
                self.publishableKey = publishableKey
                self.sessionId = sessionId
            }

            public func setLanguage(_ language: Language) -> Builder {
                self.language = language
                return self
            }

            public func setCurrency(_ currency: Currency) -> Builder {
                self.currency = currency
                return self
            }

            public func setDebugMode(_ isDebugMode: Bool) -> Builder {
                self.isDebugMode = isDebugMode
                return self
            }

            public func setConfigurableTheme(_ configurableTheme: ConfigurableTheme) -> Builder {
                self.configurableTheme = configurableTheme
                return self
            }

            public func setInlinedProcessing(_ inlinedProcessing: Bool) -> Builder {
                self.inlinedProcessing = inlinedProcessing
                return self
            }

            public func build() -> Configuration {
                .init(language: language,
                      currency: currency,
                      publishableKey: publishableKey,
                      isDebugMode: isDebugMode,
                      sessionId: sessionId,
                      redirectUrl: "komapp://",
                      appScheme: "komapp",
                      configurableTheme: configurableTheme,
                      inlinedProcessing: inlinedProcessing)
            }
        }
    }

    public struct ConfigurableTheme {
        public init(primaryColor: Color, primaryContentColor: Color, loaderColor: Color, primaryButtonCornerRadius: Int) {
            self.primaryColor = primaryColor
            self.primaryContentColor = primaryContentColor
            self.loaderColor = loaderColor
            self.primaryButtonCornerRadius = primaryButtonCornerRadius
        }

        let primaryColor: Color
        let primaryContentColor: Color
        let loaderColor: Color
        let primaryButtonCornerRadius: Int
    }

    public struct PaymentResult: Codable, Equatable {
        public let isSuccess: Bool
    }
}

extension KomojuIosSDK.PaymentResult {
    func fromMobilePaymentResult(_: KomojuMobileSDKPaymentResult) -> KomojuIosSDK.PaymentResult {
        .init(isSuccess: isSuccess)
    }
}

public extension KomojuIosSDK.Configuration {
    internal func toMobileConfiguration() -> KomojuMobileSDKConfiguration {
        .init(language: language.languageCode,
              currency: currency.currencyCode,
              publishableKey: publishableKey,
              isDebugMode: isDebugMode,
              sessionId: sessionId,
              redirectURL: redirectUrl,
              appScheme: appScheme,
              configurableTheme: configurableTheme.toMobileConfigurableTheme(),
              inlinedProcessing: inlinedProcessing)
    }

    func canProcessPayment() -> Bool {
        return toMobileConfiguration().canProcessPayment()
    }
}

private extension KomojuIosSDK.ConfigurableTheme {
    func toMobileConfigurableTheme() -> DefaultConfigurableTheme {
        .init(primaryColor: UIColor(primaryColor).toArgb(),
              primaryContentColor: UIColor(primaryContentColor).toArgb(),
              primaryShapeCornerRadiusInDp: Int32(primaryButtonCornerRadius),
              loaderColor: UIColor(loaderColor).toArgb())
    }
}

private extension DefaultConfigurableTheme {
    func toIosTheme() -> KomojuIosSDK.ConfigurableTheme {
        return KomojuIosSDK.ConfigurableTheme(
            primaryColor: Color(hex: UInt64(primaryColor)),
            primaryContentColor: Color(hex: UInt64(primaryContentColor)),
            loaderColor: Color(hex: UInt64(loaderColor)),
            primaryButtonCornerRadius: Int(primaryShapeCornerRadiusInDp)
        )
    }
}

private func hexColor(_ hex: UInt64) -> UIColor {
    UIColor(red: .init((hex & 0xFF0000) >> 16) / 255,
            green: .init((hex & 0xFF00) >> 8) / 255,
            blue: .init(hex & 0xFF) / 255,
            alpha: 1)
}

extension Color {
    init(hex: UInt64, alpha: Double = 1) {
        self.init(
            .sRGB,
            red: Double((hex >> 16) & 0xFF) / 255,
            green: Double((hex >> 08) & 0xFF) / 255,
            blue: Double((hex >> 00) & 0xFF) / 255,
            opacity: alpha
        )
    }
}

private extension UIColor {
    func toArgb() -> Int64 {
        var fRed: CGFloat = 0
        var fGreen: CGFloat = 0
        var fBlue: CGFloat = 0
        var fAlpha: CGFloat = 0
        if getRed(&fRed, green: &fGreen, blue: &fBlue, alpha: &fAlpha) {
            let iRed = Int64(fRed * 255.0)
            let iGreen = Int64(fGreen * 255.0)
            let iBlue = Int64(fBlue * 255.0)
            let iAlpha = Int64(fAlpha * 255.0)

            //  (Bits 24-31 are alpha, 16-23 are red, 8-15 are green, 0-7 are blue).
            let rgb = (iAlpha << 24) + (iRed << 16) + (iGreen << 8) + iBlue
            return rgb
        } else {
            debugPrint("Cannot convert UIColor to Int")
            return 0
        }
    }
}
