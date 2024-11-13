import Foundation

public enum Language {
    case English
    case Japanese

    public var languageCode: String {
        switch self {
        case .English: return "en"
        case .Japanese: return "ja"
        }
    }
}

public extension Language {
    var useSystemLanguage: Language {
        return if Locale.current.languageCode == "en" {
            .English
        } else {
            self
        }
    }

    func parse(from string: String) -> Language {
        return switch string {
        case "en": .English
        case "ja": .Japanese
        default: useSystemLanguage
        }
    }
}
