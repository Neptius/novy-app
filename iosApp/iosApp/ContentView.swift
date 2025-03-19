import SwiftUI
import Shared

struct ContentView: View {
    var body: some View {
        TodosScreen(viewModel: .init())
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
