export interface TodoEntry {
  id: number
  name: string
  status: TodoEntryStatus
}

export enum TodoEntryStatus {
  TODO = "TODO",
  DOING = "DOING",
  DONE = "DONE",
}
