import { TodoEntryStatus } from "@/model/TodoEntry.ts"

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
} as const
