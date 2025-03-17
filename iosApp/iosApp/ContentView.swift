import SwiftUI
import Shared

struct ContentView: View {
    let phrases = Greeting().greet()

    var body: some View {
        List(phrases, id: \.self) {
            Text($0)
        }
    }
}

#Preview {
    ContentView()
}
