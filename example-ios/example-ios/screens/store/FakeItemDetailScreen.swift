import SwiftUI

struct FakeItemDetailScreen: View {
    var item: Item
    var onBack: () -> Void
    var onFavorite: () -> Void
    var onBuyClicked: () -> Void

    init(item: Item, onBack: @escaping () -> Void, onFavorite: @escaping () -> Void, onBuyClicked: @escaping () -> Void) {
        self.item = item
        self.onBack = onBack
        self.onFavorite = onFavorite
        self.onBuyClicked = onBuyClicked
    }

    var body: some View {
        ZStack(alignment: .bottom) {
            ScrollView {
                ZStack {
                    VStack(alignment: .leading) {
                        ZStack(alignment: .top) {
                            Image(item.imageResource)
                                .resizable()
                                .aspectRatio(1, contentMode: .fill)
                                .clipShape(.rect)
                                .padding(24)
                            HStack {
                                ZStack {
                                    Image(systemName: "chevron.left")
                                        .padding(12).onTapGesture {
                                            onBack()
                                        }
                                }.background(Color.gray.opacity(0.2)).clipShape(.circle)
                                Spacer()
                                ZStack {
                                    if item.isFavorite {
                                        Image(systemName: "heart.fill")
                                            .foregroundColor(.red)
                                            .padding(12).onTapGesture {
                                                onFavorite()
                                            }
                                    } else {
                                        Image(systemName: "heart")
                                            .padding(12).onTapGesture {
                                                onFavorite()
                                            }
                                    }
                                }.background(Color.gray.opacity(0.2)).clipShape(.circle)
                            }
                        }
                        Text("¥ " + item.price).font(.system(size: 18, weight: .bold)).foregroundStyle(.black)
                        Text(item.name).font(.system(size: 20, weight: .bold)).foregroundStyle(.black)
                        HStack(spacing: 0) {
                            Text(LocalizedStringKey("model")).font(.system(size: 14)).foregroundStyle(.gray)
                            Text(": " + item.model).font(.system(size: 14)).foregroundStyle(.gray)
                            Text(", ").font(.system(size: 14)).foregroundStyle(.gray)
                            Text(item.color).font(.system(size: 14)).foregroundStyle(.gray)
                            Spacer()
                        }
                        Spacer()
                        Text(item.description).font(.system(size: 12, weight: .regular)).foregroundStyle(.gray)
                        Spacer()

                    }.padding(EdgeInsets(top: 0, leading: 16, bottom: 0, trailing: 16))
                }
            }
            Button(action: {
                onBuyClicked()
            }) {
                Text(LocalizedStringKey("buy_this_item")).padding(16)
            }.frame(maxWidth: .infinity).foregroundStyle(.white).background(Color.green).cornerRadius(16).padding(16).clipped()
        }
    }
}
