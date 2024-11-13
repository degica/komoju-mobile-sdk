import Foundation

public enum Currency {
    case JPY
    case USD

    public var currencyCode: String {
        switch self {
        case .JPY: return "JPY"
        case .USD: return "USD"
        }
    }

    func parse(from string: String) -> Currency? {
        switch string.lowercased() {
        case "jpy": return .JPY
        case "usd": return .USD
        default: return nil
        }
    }
}
