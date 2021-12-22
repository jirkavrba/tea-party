<template>
  <div>
    <v-fade-transition>
      <v-overlay class="text-center" opacity="0.9" v-if="this.connection === null">
        <v-icon size="128">mdi-connection</v-icon>
        <h1 class="mt-8">Connecting to the game server...</h1>
      </v-overlay>
    </v-fade-transition>
    <v-container v-if="this.connection !== null">
      <h1>Game</h1>
    </v-container>
  </div>
</template>

<script>
import api from "@/api";

export default {
  name: "Game",
  data: () => ({
    connection: null
  }),
  async mounted() {
    await this.$store.dispatch("loadGame", this.$route.params.id);

    if (this.$store.state.game === null) {
      await this.$router.replace({name: "Lobbies"});
      return;
    }

    this.connection = api.ws.game(this.$route.params.id);
    this.connection.subscribe(frame => {
      const message = JSON.parse(frame.body);
      console.log(message);
    });
  }
}
</script>