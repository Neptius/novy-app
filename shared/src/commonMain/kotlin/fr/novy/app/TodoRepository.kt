package fr.novy.app

interface TodoRepository {
    fun getTodos(): List<Todo>
    fun addTodo(todo: Todo)
    fun remove(todo: Todo)
    fun update(todo: Todo)
    fun toggle(todo: Todo)
}

class TodoRepositoryImpl : TodoRepository {
    private val todos = mutableListOf<Todo>()

    override fun getTodos(): List<Todo> {
        return todos
    }

    override fun addTodo(todo: Todo) {
        todos.add(todo)
    }

    override fun remove(todo: Todo) {
        todos.remove(todo)
    }

    override fun update(todo: Todo) {
        val index = todos.indexOfFirst { it.title == todo.title }
        if (index != -1) {
            todos[index] = todo
        }
    }

    override fun toggle(todo: Todo) {
        val index = todos.indexOfFirst { it.title == todo.title }
        if (index != -1) {
            todos[index] = todo.copy(completed = !todo.completed)
        }
    }
}