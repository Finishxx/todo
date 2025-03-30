<script setup lang="ts">
import type { TodoEntry } from "@/model/TodoEntry.ts"
import { computed, type ComputedRef } from "vue"

interface Props {
  entries: TodoEntry[]
  filter: string
}

const { entries, filter } = defineProps<Props>()

const emit = defineEmits<{
  delete: [id: number]
  update: [updatedEntry: TodoEntry]
  changeStatus: [entry: TodoEntry]
}>()

const filteredEntries: ComputedRef<TodoEntry[]> = computed(() => {
  if (filter == "") return entries

  return entries.filter(entry => entry.name.includes(filter))
})
</script>

<template>
  <div>
    <table class="w3-table">
      <thead>
        <tr class="w3-topbar">
          <th>Status</th>
          <th>Item</th>
        </tr>
      </thead>
    </table>
  </div>
</template>

<style scoped></style>
