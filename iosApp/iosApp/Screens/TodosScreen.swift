import Shared
import SwiftUI
import KMPObservableViewModelSwiftUI

struct TodosScreen: View {

    @EnvironmentViewModel var viewModel: TodosViewModel
    
    var body: some View {
        VStack {
            AppBar()
            
            if viewModel.todosState.loading {
                ProgressView()
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
            viewModel.getTodos()
        }
    }
    
    
    private func AppBar() -> some View {
        Text("Todos")
            .font(.largeTitle)
            .fontWeight(.bold)
    }
    
    
    private func TodoItemView(todo: Todo) -> some View {
            VStack(alignment: .leading, spacing: 8) {
                Text(todo.title)
                    .font(.title)
                    .fontWeight(.bold)
                Text(todo.description_)
                Button {
                    viewModel.removeTodo(title: todo.title)
                } label: {
                    Text("Done")
                        .bold()
                }
            }
    }


    private func ErrorMessage(message: String) -> some View {
        Text(message)
            .font(.title)
    }
}

#Preview {
    TodosScreen()
}
