import { type TodoEntry, TodoEntryStatus } from "@/model/TodoEntry.ts"
import { TodoApi } from "@/api/TodoApi.ts"

/** Business logic for the TodoEntries */
export const TodoService = {
  nextTodoState(status: TodoEntryStatus): TodoEntryStatus {
    switch (status) {
      case TodoEntryStatus.TODO:
        return TodoEntryStatus.DOING
      case TodoEntryStatus.DOING:
        return TodoEntryStatus.DONE
      case TodoEntryStatus.DONE:
        return TodoEntryStatus.TODO
    }
  },

  async loadTodos(): Promise<TodoEntry[]> {
    return await TodoApi.getAllTodos()
  },

  async postTodo(todo: TodoEntry): Promise<TodoEntry> {
    return await TodoApi.postTodo(todo)
  },

  async patchTodo(todo: TodoEntry): Promise<TodoEntry> {
    return await TodoApi.patchTodo(todo)
  },

  async deleteTodo(id: number): Promise<void> {
    await TodoApi.deleteTodo(id)
  },
} as const
