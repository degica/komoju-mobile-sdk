import SwiftUI

class FakeStoreDisplayDataRepository {
    let items = [
        Item(
            imageResource: .headphone1,
            name: LocalizedStringKey("headphone_name"),
            description: LocalizedStringKey("headphone_description"),
            price: "199",
            model: "HD1000XMG",
            color: LocalizedStringKey("gold")
        ),
        Item(
            imageResource: .headphone2,
            name: LocalizedStringKey("headphone_name"),
            description: LocalizedStringKey("headphone_description"),
            price: "199",
            model: "HD1000XMB",
            color: LocalizedStringKey("black")
        ),
        Item(
            imageResource: .headphone3,
            name: LocalizedStringKey("headphone_name"),
            description: LocalizedStringKey("headphone_description"),
            price: "199",
            model: "HD1000XMP",
            color: LocalizedStringKey("purple")
        ),
        Item(
            imageResource: .earphone1,
            name: LocalizedStringKey("earphone_name"),
            description: LocalizedStringKey("earphone_description"),
            price: "99",
            model: "E1000XMW",
            color: LocalizedStringKey("white")
        ),
        Item(
            imageResource: .earphone2,
            name: LocalizedStringKey("earphone_name"),
            description: LocalizedStringKey("earphone_description"),
            price: "99",
            model: "E1000XOW",
            color: LocalizedStringKey("off-white")
        ),
        Item(
            imageResource: .earphone3,
            name: LocalizedStringKey("earphone_name"),
            description: LocalizedStringKey("earphone_description"),
            price: "99",
            model: "E1000XMB",
            color: LocalizedStringKey("black")
        ),
        Item(
            imageResource: .earphone4,
            name: LocalizedStringKey("earphone_name"),
            description: LocalizedStringKey("earphone_description"),
            price: "99",
            model: "E1000XMB",
            color: LocalizedStringKey("blue")
        ),
        Item(
            imageResource: .mic1,
            name: LocalizedStringKey("mic_name"),
            description: LocalizedStringKey("mic_description"),
            price: "49",
            model: "M1000XMB",
            color: LocalizedStringKey("black")
        ),
        Item(
            imageResource: .mic2,
            name: LocalizedStringKey("mic_name"),
            description: LocalizedStringKey("mic_description"),
            price: "49",
            model: "M1000XMW",
            color: LocalizedStringKey("white")
        ),
        Item(
            imageResource: .mic3,
            name: LocalizedStringKey("mic_name"),
            description: LocalizedStringKey("mic_description"),
            price: "49",
            model: "M1000XMP",
            color: LocalizedStringKey("purple")
        )
    ]
}
