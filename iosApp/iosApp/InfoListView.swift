//
//  InfoListView.swift
//  iosApp
//
//  Created by 박준후 on 1/5/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct InfoListView: View {
    private struct RowItem: Hashable {
        let title: String
        let subtitle: String
    }
    
    private let items: [RowItem] = {
        let platform = Platform()
        platform.logSystemInfo()
        
        var result: [RowItem] = [
            .init(title: "운영체제", subtitle: "\(platform.osName) \(platform.osVersion)"),
            .init(title: "디바이스", subtitle: "\(platform.deviceModel)"),
            .init(title: "Density", subtitle: "@\(platform.density)x")
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
                    Text(item.subtitle)
                        .font(.body)
                        .foregroundStyle(.primary)
                }
                .padding(.vertical, 4)
                
            }
        }
    }
}

#Preview {
    InfoListView()
}
