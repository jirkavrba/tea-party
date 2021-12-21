<template>
  <div v-if="lobby !== null" class="text-center mt-16">
    <h1 class="mb-4">{{ lobby.owner.username }}'s lobby</h1>

    <v-btn v-if="joined" dark color="red" large @click="leaveLobby()"><v-icon class="mr-2">mdi-logout-variant</v-icon> Leave this lobby</v-btn>
    <v-btn v-else dark large @click="joinLobby()"><v-icon class="mr-2">mdi-login-variant</v-icon> Join this lobby</v-btn>

    <v-container>
      <v-row class="mt-16">
        <v-col v-for="(player, i) in lobby.players" :key="i" cols="12" sm="6" md="4" lg="3" xl="2">
          <v-card outlined flat>
            <v-card-title>
              <v-icon size="24" :color="lobby.owner.id === player.id ? 'black' : 'gray'">
                {{ lobby.owner.id === player.id ? 'mdi-emoticon-cool-outline' : 'mdi-emoticon-happy-outline' }}
              </v-icon>
              <span class="ml-3">{{ player.username }}</span>
              <v-chip color="black" dark class="ml-3" v-if="lobby.owner.id === player.id">host</v-chip>
            </v-card-title>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
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
  methods: {
    async joinLobby() {
      await this.$store.dispatch("joinLobby", this.lobby.id);
    },
    async leaveLobby() {
      await this.$store.dispatch("leaveLobby", this.lobby.id);
      await this.$router.push({name: "Lobbies"});
    }
  },
  computed: {
    lobby: function () {
      return this.$store.state.lobby;
    },
    joined: function () {
      return this.lobby !== null && this.lobby.players.some(player => player.id === this.$store.state.player);
    },
    owner: function () {
      return this.lobby !== null && this.lobby.owner.id === this.$store.state.player;
    }
  }
}
</script>