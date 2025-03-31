import axios from "axios"
import { type TodoEntry, TodoEntryStatus } from "../model/TodoEntry"

const URL = "http://localhost:8080/entries"

/** The same for the moment being */
type TodoEntryDto = TodoEntry

interface NewTodoEntryDto {
  name: string
  status: string
}

function entryToNewDto(entry: TodoEntry): NewTodoEntryDto {
  return { name: entry.name, status: entry.status }
}

/** Mirrors the backend's OpenAPI specification */
export const TodoApi = {
  async getTodo(id: number): Promise<TodoEntry> {
    const response = await axios.get<TodoEntry>(`${URL}/${id}`)
    return response.data
  },

  async getAllTodos(): Promise<TodoEntry[]> {
    const response = await axios.get<TodoEntry[]>(URL)
    return response.data
  },

  async postTodo(name: string, status: TodoEntryStatus): Promise<TodoEntry> {
    const response = await axios.post<TodoEntryDto>(URL, { name, status })
    return response.data
  },

  async patchTodo(todo: TodoEntry): Promise<TodoEntry> {
    const response = await axios.patch<TodoEntry>(
      `${URL}/${todo.id}`,
      entryToNewDto(todo),
    )
    return response.data
  },

  async deleteTodo(id: number): Promise<void> {
    await axios.delete(`${URL}/${id}`)
  },
} as const
