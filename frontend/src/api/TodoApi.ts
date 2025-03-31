import axios from "axios"
import { type TodoEntry } from "../model/TodoEntry"

const API_URL = "http://localhost:8080"

/** The same for the moment being */
type TodoEntryDto = TodoEntry

interface NewTodoEntryDto {
  name: string
  status: string
}

function entryToNewDto(entry: TodoEntry): NewTodoEntryDto {
  return { name: entry.name, status: entry.status }
}

export const TodoApi = {
  async getTodo(id: number): Promise<TodoEntry> {
    const response = await axios.get<TodoEntry>(`${API_URL}/${id}`)
    return response.data
  },

  async getAllTodos(): Promise<TodoEntry[]> {
    const response = await axios.get<TodoEntry[]>(API_URL)
    return response.data
  },

  async postTodo(todo: TodoEntry): Promise<TodoEntry> {
    const response = await axios.post<TodoEntryDto>(
      API_URL,
      entryToNewDto(todo),
    )
    return response.data
  },

  async patchTodo(todo: TodoEntry): Promise<TodoEntry> {
    const response = await axios.patch<TodoEntry>(
      `${API_URL}/${todo.id}`,
      entryToNewDto(todo),
    )
    return response.data
  },

  async deleteTodo(id: number): Promise<void> {
    await axios.delete(`${API_URL}/${id}`)
  },
} as const
