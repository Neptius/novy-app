import SwiftUI
import Shared

struct DebugView: View {
    private struct RowItem: Hashable {
        let title: String
        let subTitle: String
    }

    private let items: [RowItem] = {
      let platform = getPlatform()
      platform.logSystemInfo()

      var result: [RowItem] = [
        .init(title: "OS", subTitle: "\(platform.osName) \(platform.osVersion)"),
        .init(title: "Device", subTitle: platform.deviceModel),
        .init(title: "Density", subTitle: "@\(platform.density)x"),
      ]

        return result
    }()

    var body: some View {
        List {
            ForEach(items, id: \.self) { item in
                VStack(alignment: .leading) {
                    Text(item.title)
                        .font(.footnote)
                        .foregroundStyle(.secondary)
                    Text(item.subTitle)
                        .font(.body)
                        .foregroundStyle(.primary)
                }
                .padding(.vertical, 4)
            }
        }
    }
}

#Preview {
    DebugView()
}
