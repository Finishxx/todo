import { createApp } from "vue"
import App from "./App.vue"
import { createModal } from "@kolirt/vue-modal"

import Toast from "vue-toastification"

import "vue-toastification/dist/index.css"

const app = createApp(App)

// Vue-Modal
app.use(
  createModal({
    transitionTime: 200,
    animationType: "slideDown",
    modalStyle: {
      padding: "5rem 2rem",
      align: "center",
      "z-index": 201,
    },
    overlayStyle: {
      "background-color": "rgba(0, 0, 0, .5)",
      "backdrop-filter": "blur(5px)",
      "z-index": 200,
    },
  }),
)

// Vue-Toastification
app.use(Toast)

app.mount("#app")
