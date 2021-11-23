<template>
  <div :class="`w-1/2 mx-auto transition bg-${selected}-500 p-10 rounded-xl text-center`">
    <h1 class="text-4xl text-white font-black mb-10">Create a new lobby</h1>
    <div class="flex flex-row items-center justify-center">
      <div v-for="(mode, i) in ['green', 'yellow', 'red']" :key="i">
        <div
            :class="`transition transform mx-5 h-32 w-32 border-4 border-white cursor-pointer rounded-xl mb-5
              ${selected === mode ? 'bg-white bg-opacity-30 -translate-y-2' : ''}`"
            @click="select(mode)">
        </div>

        <div :class="`transition uppercase transform font-black text-xl tracking-widest text-white ${selected === mode ? 'scale-125' : 'scale-100 opacity-75'}`">
          {{ mode }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CreateLobby",
  data: () => ({
    selected: "green"
  }),
  methods: {
    select(mode) {
      this.selected = mode;
    }
  }
}
</script>