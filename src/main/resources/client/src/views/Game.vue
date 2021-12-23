<template>
  <div>
    <v-fade-transition>
      <v-overlay class="text-center" opacity="0.9" v-if="this.connection === null">
        <v-icon size="128">mdi-connection</v-icon>
        <h1 class="mt-8">Connecting to the game server...</h1>
      </v-overlay>
    </v-fade-transition>
    <v-container v-if="this.connection !== null">
      <v-row>
        <v-col cols="12" md="8">
          <v-card class="d-flex flex-column" style="min-height: 90vh">
            <v-card-title>Game</v-card-title>
            <v-card-subtitle class="text-overline">
              Playing <span class="black--text">{{ game.mode.replace("T", " t") }}</span>
            </v-card-subtitle>
            <v-divider v-if="game.round !== null"/>
            <v-card-text v-if="game.round !== null">
              <div class="text-overline">
                <h2 class="grey--text">Type in a word containing <strong class="primary--text">SYL</strong></h2>
              </div>
              <v-progress-linear value="80" max="100"></v-progress-linear>
            </v-card-text>
            <v-divider/>
            <v-card-text class="flex-grow-1">
              <v-fade-transition>
                <v-overlay absolute opacity="0.85" v-if="game.round === null" class="text-center">
                  <v-icon class="mdi-spin mb-4" size="96">mdi-yin-yang</v-icon>
                  <h1>Waiting for a new round to start...</h1>
                </v-overlay>
              </v-fade-transition>
            </v-card-text>
            <v-divider/>
            <v-card-text class="d-flex flex-row align-stretch">
              <v-text-field outlined class="flex-grow-1" hint="Tip: You can use enter to submit the word" :persistent-hint="true" autofocus></v-text-field>
              <v-btn color="primary" class="ml-2" icon x-large>
                <v-icon>mdi-chevron-double-right</v-icon>
              </v-btn>
            </v-card-text>
          </v-card>
        </v-col>
        <v-col cols="12" md="4">
          <v-card>
            <v-card-title>Score</v-card-title>
            <v-card-subtitle class="text-overline">Once one of the players hits <strong>50</strong> points, the game ends</v-card-subtitle>
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
    connection: null,
    word: ""
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