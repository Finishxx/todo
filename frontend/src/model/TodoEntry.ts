export interface TodoEntry {
  id: number
  name: string
  status: TodoEntryStatus
}

/** Keep the keys and values the same */
export enum TodoEntryStatus {
  TODO = "TODO",
  DOING = "DOING",
  DONE = "DONE",
}
