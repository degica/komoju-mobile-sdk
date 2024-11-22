import Foundation

protocol RemoteApiServices {
    func getPublishableKey(onSuccess: @escaping (String) -> Void, onFailure: @escaping (Error) -> Void)
    func createSession(
        amount: String,
        currency: String,
        language: String,
        onSuccess: @escaping (String) -> Void,
        onFailure: @escaping (Error) -> Void
    )
}

class RemoteApiServicesImpl: RemoteApiServices {
    func getPublishableKey(onSuccess: @escaping (String) -> Void, onFailure: @escaping (Error) -> Void) {
        let serveKeyUrl = (Bundle.main.object(forInfoDictionaryKey: "BASE_URL") as? String)! + "/serve-key"
        print(serveKeyUrl)
        let url = URL(string: serveKeyUrl)
        URLSession.shared.dataTask(with: url!) { data, _, error in
            guard let data = data else {
                DispatchQueue.main.async {
                    onFailure(error.unsafelyUnwrapped)
                }
                return
            }
            do {
                let decodedData = try JSONDecoder().decode(PublishableKeyResponse.self, from: data)
                DispatchQueue.main.async {
                    onSuccess(decodedData.publishableKey)
                }
            } catch {
                DispatchQueue.main.async {
                    onFailure(error)
                }
            }
        }.resume()
    }

    func createSession(
        amount: String,
        currency: String,
        language: String,
        onSuccess: @escaping (String) -> Void,
        onFailure: @escaping (Error) -> Void
    ) {
        let url = URL(string: (Bundle.main.object(forInfoDictionaryKey: "BASE_URL") as? String)! + "/create-session")
        let body = [
            "amount": amount,
            "currency": currency,
            "language": language
        ]
        let headers = [
            "Content-Type": "application/json",
            "Accept": "application/json"
        ]
        var request = URLRequest(url: url!)
        request.httpMethod = "POST"
        request.httpBody = try? JSONSerialization.data(withJSONObject: body, options: [])
        request.allHTTPHeaderFields = headers
        URLSession.shared.dataTask(with: request) { data, _, error in
            guard let data = data else {
                onFailure(error.unsafelyUnwrapped)
                return
            }
            do {
                let decodedData = try JSONDecoder().decode(CreateSessionResponse.self, from: data)
                DispatchQueue.main.async {
                    onSuccess(decodedData.sessionId)
                }
            } catch {
                DispatchQueue.main.async {
                    onFailure(error)
                }
            }
        }.resume()
    }
}

func remoteApiServices() -> RemoteApiServices {
    return RemoteApiServicesImpl()
}
