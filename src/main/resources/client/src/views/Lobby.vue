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
    await this.$store.dispatch("joinLobby", this.$route.params.id)

    // If the lobby is not loaded, return to lobbies listing (or authentication)
    if (this.$store.state.lobby === null) {
      await this.$router.replace("/");
      return;
    }
  }
});
</script>