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
      <v-row>
        <v-col cols="12" md="8"></v-col>
        <v-col cols="12" md="4">
          <v-card>
            <v-card-title>Score</v-card-title>
            <v-card-subtitle>Once one of the players hits <strong>50</strong> points, the game ends</v-card-subtitle>
            <v-divider/>
            <v-card-text>
              <v-row>
                <v-col v-for="(entry, i) in scores" cols="12" :key="i">
                  <v-card outlined>
                    <v-card-text class="d-flex flex-row align-center">
                      <strong class="text-h5 primary--text">{{ entry.score }}</strong>
                      <v-divider vertical class="mx-4"/>
                      <span class="text-overline">{{ player(entry.player).username }}</span>
                    </v-card-text>
                  </v-card>
                </v-col>
              </v-row>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
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
  },
  methods: {
    player(id) {
      return this.game.players.find(player => player.id === id);
    },
  },
  computed: {
    scores: function () {
      return [...this.game.scores].sort((a, b) => a.score - b.score);
    },
    game: function () {
      return this.$store.state.game;
    }
  }
}
</script>