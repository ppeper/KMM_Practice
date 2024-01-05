//
//  InfoScreen.swift
//  iosApp
//
//  Created by 박준후 on 1/5/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct InfoScreen: View {
    var body: some View {
        NavigationStack {
            InfoListView()
                .navigationTitle("디바이스 정보")
        }
    }
}

#Preview {
    InfoScreen()
}
