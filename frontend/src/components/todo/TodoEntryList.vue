<script setup lang="ts">
import { type TodoEntry, TodoEntryStatus } from "@/model/TodoEntry.ts"
import { computed, type ComputedRef } from "vue"

interface Props {
  entries: TodoEntry[]
  filter: string
}

const { entries, filter } = defineProps<Props>()

// eslint-disable-next-line @typescript-eslint/no-unused-vars
const emits = defineEmits<{
  add: []
  delete: [entry: TodoEntry]
  update: [entry: TodoEntry]
  changeStatus: [entry: TodoEntry]
}>()

const filteredEntries: ComputedRef<TodoEntry[]> = computed(() => {
  if (filter == "") return entries

  return entries.filter(entry =>
    entry.name.toLowerCase().includes(filter.toLowerCase()),
  )
})
</script>

<template>
  <div>
    <table class="w3-table">
      <thead>
        <tr class="w3-topbar">
          <th>Status</th>
          <th>Name</th>
          <th class="w3-right-align">
            <button
              @click="$emit('add')"
              class="w3-button w3-blue w3-round-large add-button fa-add"
            >
              Add new TODO
            </button>
          </th>
        </tr>
      </thead>
      <tbody>
        <!-- Change entry status -->
        <tr v-for="entry in filteredEntries" :key="entry.id">
          <td
            class="clickable w3-hover-pale-blue"
            @click="$emit('changeStatus', entry)"
          >
            <i
              class="far fa-2x fa-square w3-grey w3-padding-small"
              v-if="entry.status == TodoEntryStatus.TODO"
            ></i>
            <i
              class="fas fas fa-2x fa-square-minus w3-teal w3-padding-small"
              v-if="entry.status == TodoEntryStatus.DOING"
            ></i>
            <i
              class="far fa-2x fa-square-check w3-green w3-padding-small"
              v-if="entry.status == TodoEntryStatus.DONE"
            ></i>
          </td>
          <!-- Entry name -->
          <td>{{ entry.name }}</td>
          <!-- Entry modifying buttons -->
          <td class="w3-right-align">
            <button class="w3-button w3-teal" @click="$emit('update', entry)">
              Edit
            </button>
            <button
              class="w3-button w3-red fa-cross"
              @click="$emit('delete', entry)"
            >
              Delete
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped></style>
