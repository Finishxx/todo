<script setup lang="ts">
import type { TodoEntry } from "@/model/TodoEntry.ts"

const props = defineProps<{
  entries: TodoEntry[]
}>()

const emit = defineEmits<{
  delete: [id: number]
  update: [updatedEntry: TodoEntry]
}>()
</script>

<template></template>

<style scoped></style>
