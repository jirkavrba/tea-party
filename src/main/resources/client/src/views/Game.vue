<template>
  <div>
    Game
  </div>
</template>

<script>
export default {
  name: "Game",
  async mounted() {
    await this.$store.dispatch("loadGame", this.$route.params.id);

    if (this.$store.state.game === null) {
      await this.$router.replace({name: "Lobbies"});
      return;
    }
  }
}
</script>