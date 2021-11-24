<template>
  <div>
    <h1>Lobby</h1>
  </div>
</template>
<script>
import {defineComponent} from "vue";

export default defineComponent({
  name: "Lobby",
  async mounted() {
    await this.$store.dispatch("loadLobby", this.$route.params.id)
  }
});
</script>