<template>
  <div class="text-center mt-16">
    <h1>Lobbies</h1>
    <v-btn :to="{name: 'CreateLobby'}" color="black" dark>Create a new lobby</v-btn>
  </div>
</template>

<script>
export default {
  name: "Lobbies",
  async mounted() {
    await this.$store.dispatch("loadLobbies");
  },
  computed: {
    lobbies: function () {
      return this.$store.state.lobbies;
    }
  }
}
</script>