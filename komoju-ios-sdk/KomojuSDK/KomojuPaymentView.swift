import komojuShared
import SwiftUI
import UIKit

public struct KomojuPaymentView: View {
    @State var isVisible: Bool = false
    @State private var sheetHeight: CGFloat = .zero
    @State private var deeplinkUrl: String?

    let configuration: KomojuIosSDK.Configuration
    let onDismiss: (KomojuIosSDK.PaymentResult) -> Void
    public init(configuration: KomojuIosSDK.Configuration, onDismiss: @escaping (KomojuIosSDK.PaymentResult) -> Void) {
        self.configuration = configuration
        self.onDismiss = onDismiss
    }

    public var body: some View {
        Spacer().onAppear {
            isVisible = true
        }
        .sheet(isPresented: $isVisible, onDismiss: {
            isVisible = false
        }, content: {
            ComposeView(
                configuration: configuration.toMobileConfiguration(),
                onDismiss: onDismiss,
                deepLinkUrl: deeplinkUrl)
            .ignoresSafeArea(.keyboard)
        }).onOpenURL(perform: onNewDeeplink)
    }

    private func onNewDeeplink(_ url: URL) {
        deeplinkUrl = url.absoluteString
    }
}

private struct ComposeView: UIViewControllerRepresentable {
    let configuration: KomojuMobileSDKConfiguration
    let onDismiss: (KomojuIosSDK.PaymentResult) -> Void
    let deepLinkUrl: String?
    func makeUIViewController(context _: Context) -> UIViewController {
        MainViewControllerKt.MainViewController(configuration: configuration) { result in
            self.onDismiss(KomojuIosSDK.PaymentResult(isSuccess: result.isSuccessFul))
        }
    }

    func updateUIViewController(_: UIViewController, context _: Context) {}
}
