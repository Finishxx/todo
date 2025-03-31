<script setup lang="ts">
import { type TodoEntry, TodoEntryStatus } from "@/model/TodoEntry.ts"
import { computed, type ComputedRef } from "vue"

interface Props {
  entries: TodoEntry[]
}

const { entries } = defineProps<Props>()

// TODO: Could be done in one pass through the entries by mutating the Map
const todoStatusCounts: ComputedRef<Map<TodoEntryStatus, number>> = computed(
  () =>
    new Map(
      Object.values(TodoEntryStatus).map(status => {
        const count = entries.filter(entry => entry.status == status).length
        return [status, count]
      }),
    ),
)
</script>

<template>
  <div class="summary-wrapper">
    <div class="w3-gray w3-center">
      <h1>TODO</h1>
      <h2>{{ todoStatusCounts.get(TodoEntryStatus.TODO) }}</h2>
    </div>
    <div class="w3-teal w3-center">
      <h1>Doing</h1>
      <h2>{{ todoStatusCounts.get(TodoEntryStatus.DOING) }}</h2>
    </div>
    <div class="w3-green w3-center">
      <h1>Done</h1>
      <h2>{{ todoStatusCounts.get(TodoEntryStatus.DONE) }}</h2>
    </div>
  </div>
</template>

<style scoped>
.summary-wrapper {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 1rem;
  padding: 1rem;
}
</style>
