import SwiftUI

struct DebugScreen: View {
    
    @Environment(\.dismiss) private var dismiss
    
    var body: some View {
        NavigationStack {
            DebugView()
                .navigationTitle("[DEBUG]")
                .toolbar {
                    ToolbarItem {
                        Button {
                            dismiss()
                        } label: {
                            Text("Done")
                                .bold()
                        }
                    }
                }
        }
    }
}

#Preview {
    DebugScreen()
}
