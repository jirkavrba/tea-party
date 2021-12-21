<template>
  <div>
    Joined: {{ joined ? 'true' : 'false' }}
  </div>
</template>

<script>
export default {
  name: "Lobby",
  async mounted() {
    await this.$store.dispatch("loadLobby", this.$route.params.id);

    if (this.lobby === null) {
      await this.$router.replace({name: "Lobbies"});
    }
  },
  computed: {
    lobby: function () {
      return this.$store.state.lobby;
    },
    joined: function () {
      return this.lobby !== null && this.lobby.players.some(player => player.id === this.$store.state.player);
    }
  }
}
</script>