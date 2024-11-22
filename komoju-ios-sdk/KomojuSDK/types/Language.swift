import Foundation

public enum Language {
    case english
    case japanese

    public var languageCode: String {
        switch self {
        case .english: return "en"
        case .japanese: return "ja"
        }
    }
}

public extension Language {
    var useSystemLanguage: Language {
        return if Locale.current.languageCode == "en" {
            .english
        } else {
            self
        }
    }

    func parse(from string: String) -> Language {
        return switch string {
        case "en": .english
        case "ja": .japanese
        default: useSystemLanguage
        }
    }
}
