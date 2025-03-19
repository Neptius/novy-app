import SwiftUI

struct DebugScreen: View {
    var body: some View {
        NavigationStack {
            DebugView()
                .navigationTitle("[DEBUG]")
        }
    }
}

#Preview {
    DebugScreen()
}
