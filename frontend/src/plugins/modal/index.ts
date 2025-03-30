import {
  reactive,
  type App,
  type Plugin,
  type Reactive,
  type InjectionKey,
} from "vue"
import Modal from "./Modal.vue"
import type { Options } from "@vitejs/plugin-vue"

interface ModalObject {
  name: string
  resolve: (any: unknown) => void
  reject: (any: unknown) => void
}

export interface ModalApi {
  activeModalsName(): string

  show(name: string): Promise<unknown>

  accept(): void

  cancel(): void
}

const current: Reactive<ModalObject> = reactive({
  name: "",
  resolve: () => {},
  reject: () => {},
})

const api: ModalApi = {
  activeModalsName(): string {
    return current.name
  },
  show(name: string): Promise<unknown> {
    current.name = name
    return new Promise((resolve = () => {}, reject = () => {}) => {
      current.resolve = resolve
      current.reject = reject
    })
  },
  accept(): void {
    current.resolve(null)
    current.name = ""
  },
  cancel(): void {
    current.reject(null)
    current.name = ""
  },
}

export const modalApiKey = Symbol() as InjectionKey<ModalApi>
export const plugin: Plugin = {
  install(App: App, options: Options): any {
    // Register as component
    App.component("Modal", Modal)
    // Provide the API
    App.provide(modalApiKey, api)
  },
}
