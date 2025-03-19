import SwiftUI
import Shared

extension TodosScreen {
    @MainActor
    class TodosViewModelWrapper: ObservableObject {
        let todosViewModel: TodosViewModel
        
        init() {
            todosViewModel = TodosViewModel()
            todosState = todosViewModel.todosState.value
        }
        
        @Published var todosState: TodosState
        
        func startObserving() {
            Task {
                for await todosS in todosViewModel.todosState {
                    self.todosState = todosS
                }
            }
        }
    }
}

struct TodosScreen: View {
    
    @ObservedObject private(set) var viewModel: TodosViewModelWrapper
    
    var body: some View {
        VStack {
            AppBar()
            
            if viewModel.todosState.loading {
                Loader()
            }
            
            if let error = viewModel.todosState.error {
                ErrorMessage(message: error)
            }
            
            if(!viewModel.todosState.todos.isEmpty) {
                ScrollView {
                    LazyVStack (alignment: .leading, spacing: 10) {
                        ForEach(viewModel.todosState.todos, id: \.self) { todo in
                            TodoItemView(todo: todo)
                        }
                    }
                }
            }
        }.onAppear {
            self.viewModel.startObserving()
        }
    }
}

struct AppBar: View {
    var body: some View {
        Text("Todos")
            .font(.largeTitle)
            .fontWeight(.bold)
    }
}

struct TodoItemView: View {
    var todo: Todo
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(todo.title)
                .font(.title)
                .fontWeight(.bold)
            Text(todo.description_)
        }
    }
}

struct Loader: View {
    var body: some View {
        ProgressView()
    }
}

struct ErrorMessage: View {
    var message: String
    var body: some View {
        Text(message)
            .font(.title)
    }
}

#Preview {
    TodosScreen(viewModel: .init())
}
