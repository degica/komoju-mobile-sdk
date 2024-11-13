import ios
import SwiftUI

struct FakeStoreView: View {
    @StateObject private var viewModel = FakeStoreViewModel()

    var body: some View {
        ZStack {
            if viewModel.uiState.items.isEmpty {
                ZStack(alignment: .center) {
                    ProgressView().onAppear {
                        Task {
                            await viewModel.fetchData()
                        }
                    }
                }
            } else {
                VStack(alignment: .leading) {
                    HStack(spacing: 0) {
                        Text(LocalizedStringKey("sound")).font(.system(size: 24))
                        Text(LocalizedStringKey("bud")).font(.system(size: 24)).fontWeight(.bold).foregroundStyle(.green)
                        Spacer()
                    }.padding(EdgeInsets(top: 0, leading: 16, bottom: 0, trailing: 10))
                    Text(LocalizedStringKey("description")).font(.system(size: 12)).padding(EdgeInsets(top: 0, leading: 16, bottom: 0, trailing: 10))
                    ScrollView {
                        LazyVGrid(columns: Array(repeating: GridItem(), count: 2)) {
                            ForEach(viewModel.uiState.items) { item in
                                FakeStoreItemView(item: item) {
                                    viewModel.onFavIconClicked(item: item)
                                }.onTapGesture {
                                    viewModel.onItemClicked(item: item)
                                }
                            }
                        }
                    }.padding(EdgeInsets(top: 8, leading: 8, bottom: 0, trailing: 8))
                    Spacer()
                }
            }
            ZStack(alignment: .leading) {
                if viewModel.itemDetail != nil {
                    FakeItemDetailScreen(item: viewModel.itemDetail!, onBack: {
                        viewModel.closeItemDetail()
                    }, onFavorite: {
                        viewModel.onFavIconClicked(item: viewModel.itemDetail!)
                    }, onBuyClicked: {
                        viewModel.createSession(item: viewModel.itemDetail!)
                    })
                }
            }.background(Color.white)
                .transition(.move(edge: .trailing))
                .animation(.easeInOut, value: viewModel.itemDetail)

            ZStack(alignment: .leading) {
                if viewModel.paymentResult != nil {
                    if viewModel.paymentResult!.isSuccess {
                        FakeStoreSuccessView(onBack: {
                            viewModel.clearResult()
                        })
                    } else {
                        FakeStoreFailedView(onBack: {
                            viewModel.clearResult()
                        })
                    }
                }
            }.background(Color.white)
                .transition(.move(edge: .trailing))
                .animation(.easeInOut, value: viewModel.paymentResult)

            if viewModel.uiState.isCreatingSession {
                ZStack {
                    ProgressView().padding(32).background(Color.gray.opacity(0.5).blur(radius: 2)).cornerRadius(16)
                }
            }
            if viewModel.komojuPaymentConfiguration?.canProcessPayment() ?? false {
                KomojuPaymentView(configuration: viewModel.komojuPaymentConfiguration!) { result in
                    viewModel.onPaymentResultReceived(result: result)
                    debugPrint("Payment Result -> \(result.isSuccess)")
                }
            }
        }
    }
}

struct FakeStoreItemView: View {
    var item: Item
    var onClick: () -> Void

    init(item: Item, onClick: @escaping () -> Void) {
        self.item = item
        self.onClick = onClick
    }

    var body: some View {
        ZStack(alignment: .topTrailing) {
            VStack(alignment: .leading) {
                Image(item.imageResource)
                    .resizable()
                    .aspectRatio(contentMode: .fill)
                    .frame(
                        minWidth: 0,
                        maxWidth: .infinity,
                        minHeight: 0,
                        maxHeight: .infinity
                    )
                    .aspectRatio(0.77, contentMode: ContentMode.fit)
                    .clipShape(.rect)
                    .padding(24)

                Text("¥ " + item.price).font(.system(size: 18, weight: .bold)).foregroundStyle(.black)
                Text(item.name).font(.system(size: 14, weight: .medium)).foregroundStyle(.black)
                HStack(spacing: 0) {
                    Text(LocalizedStringKey("model")).font(.system(size: 12)).foregroundStyle(.gray)
                    Text(": " + item.model).font(.system(size: 12)).foregroundStyle(.gray)
                    Text(", ").font(.system(size: 12)).foregroundStyle(.gray)
                    Text(item.color).font(.system(size: 12)).foregroundStyle(.gray)
                    Spacer()
                }
            }
            ZStack {
                if item.isFavorite {
                    Image(systemName: "heart.fill")
                        .foregroundColor(.red)
                        .padding(12).onTapGesture {
                            onClick()
                        }
                } else {
                    Image(systemName: "heart")
                        .padding(12).onTapGesture {
                            onClick()
                        }
                }
            }.background(Color.gray.opacity(0.2)).clipShape(.circle)

        }.padding(8)
    }
}

#Preview {
    FakeStoreView()
}
