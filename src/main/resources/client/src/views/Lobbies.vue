<template>
  <div>
    <div class="mb-10">
      <router-link to="/create-lobby" class="flex flex-col items-center">
        <button class="bg-green-500 px-10 py-5 rounded-xl cursor-pointer text-white font-black text-2xl hover:bg-green-400 shadow">Create a new lobby</button>
      </router-link>
    </div>
    <div v-if="lobbies.length === 0" class="text-center text-3xl text-gray-500 mt-20">
      <i class="far fa-sad-cry mr-5"></i>
      <span class="inline-block pb-10">Sadly, there are no lobbies yet&hellip;</span><br>
      <strong class="text-gray-300">Click the button above to create a new one!</strong>
    </div>
  </div>
</template>
<script>
export default {
  name: "Lobbies",
  async mounted() {
    await this.$store.dispatch("loadLobbies")
  },
  methods: {
    async loadLobbies() {
      await this.$store.dispatch("loadLobbies");
    }
  },
  computed: {
    lobbies: function () {
      return this.$store.state.lobbies;
    }
  }
}
</script>