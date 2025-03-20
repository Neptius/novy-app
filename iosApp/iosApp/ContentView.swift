import SwiftUI
import Shared

struct ContentView: View {
    
    @State private var shouldOpenDebug = false
    
    var body: some View {
        NavigationStack {
            TodosScreen()
                .toolbar {
                    ToolbarItem {
                        Button {
                            shouldOpenDebug = true
                        } label: {
                            Label("Debug", systemImage: "info.circle").labelStyle(.titleAndIcon)
                        }
                        .popover(isPresented: $shouldOpenDebug) {
                            DebugScreen()
                        }
                    }
                }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
