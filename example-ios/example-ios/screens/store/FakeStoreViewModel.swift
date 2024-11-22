import Foundation
import KomojuSDK
import SwiftUI

class FakeStoreViewModel: ObservableObject {
    let apiServices = remoteApiServices()
    var publisherKey: String?
    @Published var uiState: FakeStoreUiState = .init()
    @Published var itemDetail: Item?
    @Published var komojuPaymentConfiguration: KomojuIosSDK.Configuration?
    @Published var paymentResult: KomojuIosSDK.PaymentResult?

    func fetchData() async {
        apiServices.getPublishableKey { key in
            self.uiState.items = FakeStoreDisplayDataRepository().items
            self.publisherKey = key
            debugPrint(key)
        } onFailure: { error in
            self.uiState.error = error.localizedDescription
            debugPrint(error)
        }
    }

    func onFavIconClicked(item: Item) {
        uiState.items = uiState.items.map { it in
            if it.id == item.id {
                return Item(
                    id: it.id,
                    imageResource: it.imageResource,
                    name: it.name,
                    description: it.description,
                    price: it.price,
                    model: it.model,
                    color: it.color,
                    isFavorite: !it.isFavorite
                )
            } else {
                return it
            }
        }
        if itemDetail != nil {
            itemDetail?.isFavorite.toggle()
        }
    }

    func onItemClicked(item: Item) {
        itemDetail = item
    }

    func closeItemDetail() {
        itemDetail = nil
    }

    func createSession(item: Item) {
        uiState.isCreatingSession = true
        Task {
            await createSessionAsync(item: item)
        }
    }

    private func createSessionAsync(item: Item) async {
        apiServices.createSession(amount: item.price, currency: Currency.JPY.currencyCode, language: Language.english.useSystemLanguage.languageCode, onSuccess: { sessionId in
            self.uiState.isCreatingSession = false
            self.komojuPaymentConfiguration = KomojuIosSDK.Configuration.Builder(
                publishableKey: self.publisherKey!,
                sessionId: sessionId
            ).setConfigurableTheme(KomojuIosSDK.ConfigurableTheme(
                primaryColor: Color.green,
                primaryContentColor: Color.white,
                loaderColor: Color.green,
                primaryButtonCornerRadius: 16
            ))
            .setInlinedProcessing(true)
            .setLanguage(Language.english.useSystemLanguage)
            .setCurrency(Currency.JPY)
            .setDebugMode(true)
            .build()
        }, onFailure: { error in
            self.uiState.isCreatingSession = false
            debugPrint(error)
        })
    }

    func onPaymentResultReceived(result: KomojuIosSDK.PaymentResult) {
        komojuPaymentConfiguration = nil
        paymentResult = result
    }

    func clearResult() {
        paymentResult = nil
    }
}
