<script setup lang="ts">
import { type TodoEntry, TodoEntryStatus } from "@/model/TodoEntry.ts"
import { computed, type ComputedRef } from "vue"

interface Props {
  entries: TodoEntry[]
  filter: string
}

const { entries, filter } = defineProps<Props>()

const emits = defineEmits<{
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
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="entry in filteredEntries" :key="entry.id">
          <td class="button" @click="$emit('changeStatus', entry)">
            <i
              class="fa-2x fa-square w3-grey"
              v-if="entry.status == TodoEntryStatus.TODO"
            ></i>
            <i
              class="fa-2x fa-minus w3-teal"
              v-if="entry.status == TodoEntryStatus.DOING"
            ></i>
            <i
              class="fa-2x fa-check w3-green"
              v-if="entry.status == TodoEntryStatus.DONE"
            ></i>
          </td>
          <td>{{ entry.name }}</td>
          <td></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped></style>
