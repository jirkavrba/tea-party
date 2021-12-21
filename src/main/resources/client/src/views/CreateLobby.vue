<template>
  <div class="text-center mt-16">
    <v-row class="mt-16">
      <v-col offset="1" offset-md="3" offset-lg="4" md="6" lg="4" cols="10">
        <v-card>
          <v-card-title>
            <router-link :to="{name: 'Lobbies'}" class="mr-4">
              <v-icon>mdi-arrow-left</v-icon>
            </router-link>
            Create a new lobby
          </v-card-title>
          <v-card-text>
            <div class="text-overline my-8">Select game mode</div>
            <v-row>
              <v-col md="4">
                <v-card hover :color="selected === 'GreenTea' ? 'green' : 'gray'" :dark="selected === 'GreenTea'" outlined @click="selected = 'GreenTea'">
                  <v-card-text>
                    <v-icon>mdi-timer-outline</v-icon>
                    <div class="text-center text-overline">Green tea</div>
                  </v-card-text>
                </v-card>
              </v-col>
              <v-col md="4">
                  <v-card hover :color="selected === 'YellowTea' ? 'yellow darken-3' : 'gray'" :dark="selected === 'YellowTea'" outlined @click="selected = 'YellowTea'">
                  <v-card-text>
                    <v-icon>mdi-abacus</v-icon>
                    <div class="text-center text-overline">Yellow tea</div>
                  </v-card-text>
                </v-card>
              </v-col>
              <v-col md="4">
                  <v-card hover :color="selected === 'RedTea' ? 'red' : 'gray'" :dark="selected === 'RedTea'" outlined @click="selected = 'RedTea'">
                  <v-card-text>
                    <v-icon>mdi-ruler</v-icon>
                    <div class="text-center text-overline">Red tea</div>
                  </v-card-text>
                </v-card>
              </v-col>
              <v-col cols="12" class="mt-4">
                <v-btn block :disabled="selected === null" color="black" :dark="selected !== null" large @click="createLobby()">Create lobby</v-btn>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
  name: "CreateLobby",
  data: () => ({
    selected: null
  }),
  methods: {
    async createLobby() {
      const lobby = await this.$store.dispatch("createLobby", this.selected);

      if (lobby !== null) {
        await this.$router.push(`/lobby/${lobby.id}`);
      }
    }
  }
}
</script>