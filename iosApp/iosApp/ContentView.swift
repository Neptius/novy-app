import SwiftUI
import Shared

struct ContentView: View {
    @State private var showContent = false
    var body: some View {
        DebugScreen()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
