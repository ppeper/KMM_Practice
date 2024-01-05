import SwiftUI

struct ContentView: View {

    @State private var shouldOpenInfo = false
    
	var body: some View {
        NavigationStack {
            ArticleScreen(viewModel: .init())
                .toolbar {
                    ToolbarItem {
                        Button {
                            shouldOpenInfo = true
                        } label: {
                            Label("정보", systemImage: "info.circle")
                                .labelStyle(.titleAndIcon)
                        }
                        .popover(isPresented: $shouldOpenInfo) {
                            InfoScreen()
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
