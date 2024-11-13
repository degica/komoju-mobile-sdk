import Foundation
import SwiftUI

struct FakeStoreUiState {
    var items: [Item] = []
    var isCreatingSession: Bool = false
    var error: String?
}

struct Item: Identifiable, Equatable {
    var id: UUID = .init()
    var imageResource: ImageResource
    var name: LocalizedStringKey
    var description: LocalizedStringKey
    var price: String
    var model: String
    var color: LocalizedStringKey
    var isFavorite: Bool = false
}
