package fr.novy.app

class TodoPresenter(private val todoRepository: TodoRepository) {
    fun getTodos(): List<Todo> = todoRepository.getTodos()
    fun addTodo(todo: Todo) = todoRepository.addTodo(todo)
    fun remove(todo: Todo) = todoRepository.remove(todo)
    fun toggle(todo: Todo) = todoRepository.toggle(todo)
}