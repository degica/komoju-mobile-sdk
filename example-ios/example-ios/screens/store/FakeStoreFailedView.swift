import SwiftUI

struct FakeStoreFailedView: View {
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
            Image(systemName: "xmark.octagon.fill").resizable().foregroundColor(.red).frame(width: 120, height: 120).padding(16)
            Text(LocalizedStringKey("order_failed_title")).fontWeight(.bold).font(.system(size: 24)).padding(16)
            Text(LocalizedStringKey("order_failed_description")).font(.system(size: 16)).multilineTextAlignment(.center)
            Spacer()
            Button(action: {
                onBack()
            }) {
                Text(LocalizedStringKey("retry_payment")).padding(16)
            }.frame(maxWidth: .infinity).foregroundStyle(.white).background(Color.green).cornerRadius(16).padding(16).clipped()

            Button(action: {
                onBack()
            }) {
                Text(LocalizedStringKey("close")).padding(16)
            }.foregroundStyle(.blue)
            Spacer()
        }.padding(16)
    }
}

#Preview {
    FakeStoreFailedView(onBack: {

    })
}
