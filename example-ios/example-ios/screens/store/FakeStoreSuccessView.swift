import SwiftUI

struct FakeStoreSuccessView: View {
    var onBack: () -> Void

    init(onBack: @escaping () -> Void) {
        self.onBack = onBack
    }
    var body: some View {
        VStack {
            HStack {
                Button(action: {
                    onBack()
                }, label: {
                    HStack(spacing: 8) {
                        Image(systemName: "arrow.left")
                    }
                })
                .accentColor(.blue)
                Spacer()
            }
            Spacer()
            Image(systemName: "checkmark.seal.fill").resizable().foregroundColor(.green).frame(width: 120, height: 120).padding(16)
            Text(LocalizedStringKey("order_confirmed")).fontWeight(.bold).font(.system(size: 24)).padding(16)
            Text(LocalizedStringKey("thank_you_for_your_order")).font(.system(size: 16)).multilineTextAlignment(.center)
            Spacer()
            Button(action: {
                onBack()
            }) {
                Text(LocalizedStringKey("continue_shopping")).padding(16)
            }.frame(maxWidth: .infinity).foregroundStyle(.white).background(Color.green).cornerRadius(16).padding(16).clipped()

            Button(action: {
                onBack()
            }) {
                Text(LocalizedStringKey("share")).padding(16)
            }.foregroundStyle(.blue)
            Spacer()
        }.padding(16)
    }
}

#Preview {
    FakeStoreSuccessView(onBack: {

    })
}
