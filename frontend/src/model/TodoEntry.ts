export interface TodoEntry {
  id: number
  name: string
  status: TodoEntryStatus
}

/** Do please keep the keys and values the same */
export enum TodoEntryStatus {
  TODO = "TODO",
  DOING = "DOING",
  DONE = "DONE",
}
