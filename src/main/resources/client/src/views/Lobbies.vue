<template>
  <div>
    <div class="mb-10">
      <router-link to="/create-lobby" class="flex flex-col items-center">
        <button class="bg-green-500 px-10 py-5 rounded-xl cursor-pointer text-white font-black text-2xl hover:bg-green-400 shadow">
          Create a new lobby
        </button>
      </router-link>
    </div>
    <div v-if="lobbies.length === 0" class="text-center text-3xl text-gray-500 mt-20">
      <i class="far fa-sad-cry mr-5"></i>
      <span class="inline-block pb-10">Sadly, there are no lobbies yet&hellip;</span><br>
      <strong class="text-gray-300">Click the button above to create a new one!</strong>
    </div>
    <div v-else class="flex flex-row flex-wrap items-center justify-center">
      <router-link :to="`/lobby/${lobby.id}`" v-for="(lobby, i) in lobbies" :key="i"
                   :class="`w-96 m-5 p-10 rounded-xl border-4 flex flex-col items-center justify-between ${color(lobby.mode, 'border')} transition ${color(lobby.mode, 'bg')} bg-opacity-5 hover:bg-opacity-20 cursor-pointer`">
        <div :class="`flex flex-row items-center justify-center ${color(lobby.mode, 'bg')} bg-opacity-20 w-24 h-24 rounded-full`">
          <i :class="`fas ${icon(lobby.mode)} text-4xl ${color(lobby.mode, 'text')}`"></i>
        </div>
        <div :class="`my-10 text-4xl font-black ${color(lobby.mode, 'text')}`">{{ title(lobby.mode) }}</div>
        <div class="uppercase font-black text-sm tracking-widest font-mono text-white">{{ lobby.players }} player/s</div>
      </router-link>
    </div>
  </div>
</template>
<script>
import {icon, title, color} from "../helpers/mode"

export default {
  name: "Lobbies",
  data: () => ({
    intervalHook: null
  }),
  async mounted() {
    await this.$store.dispatch("loadLobbies");
    this.intervalHook = window.setInterval(this.loadLobbies, 10000);
  },
  beforeUnmount() {
    window.clearInterval(this.intervalHook);
  },
  methods: {
    async loadLobbies() {
      await this.$store.dispatch("loadLobbies");
    },
    icon: (mode) => icon(mode),
    title: (mode) => title(mode),
    color: (mode, component) => color(mode, component),
  },
  computed: {
    lobbies: function () {
      return this.$store.state.lobbies;
    }
  }
}
</script>