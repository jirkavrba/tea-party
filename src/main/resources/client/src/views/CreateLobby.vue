<template>
  <div :class="`w-1/2 mx-auto transition bg-gray-700 p-10 rounded-xl text-center`">
    <h1 class="text-4xl text-white font-black mb-10">Create a new lobby</h1>
    <div class="flex flex-row items-center justify-center">
      <div v-for="(mode, i) in ['green', 'yellow', 'red']" :key="i">
        <div :class="`flex flex-row items-center justify-center transition transform mx-5 h-32 w-32 border-0 cursor-pointer rounded-full mb-5
              ${color(mode, 'border')} ${color(mode, 'bg')}
              ${selected === mode
                  ? 'scale-110'
                  : 'bg-opacity-30'
              }`"
             @click="select(mode)">
          <i :class="`transition transform text-5xl ${icon(mode)} ${selected === mode ? 'text-white' : 'opacity-80 ' + color(mode, 'text') }`"></i>
        </div>

        <div :class="`transition transform font-black text-2xl text-white ${selected === mode ? '' : 'scale-100 opacity-50'}`">
          {{ title(mode) }}
        </div>

      </div>
    </div>

    <button :class="`mt-10 transition px-20 py-5 rounded-xl text-white text-2xl font-black ${color(selected, 'bg')} hover:bg-opacity-80`" @click="submit()">
      Continue
      <i class="fas fa-arrow-right ml-5"></i>
    </button>
  </div>
</template>

<script>
import {alias, icon, title, color} from "../helpers/mode"
export default {
  name: "CreateLobby",
  data: () => ({
    selected: "green"
  }),
  methods: {
    select(mode) {
      this.selected = mode;
    },
    async submit() {
      const lobby = await this.$store.dispatch("createLobby", this.selected)
      await this.$router.replace(`/lobby/${lobby.id}`)
    },
    icon: (mode) => icon(alias(mode)),
    title: (mode) => title(alias(mode)),
    color: (mode, component) => color(alias(mode), component),
  }
}
</script>