<template>
  <div class="flex flex-col items-center mt-10 relative">
    <div :class="`w-1/2 bg-green-500 px-10 py-16 rounded-xl shadow-xl flex flex-col items-center transform transition ${loading ? 'scale-90' : ''}`">
      <h1 class="text-4xl text-green-800 font-black">Pick your username</h1>

      <input type="text" class="mt-10 w-full p-5 rounded-xl ring-4 ring-green-400 bg-green-50 text-center font-black text-3xl transition
      focus:bg-white focus:outline-none hover:border-green-300 focus:ring-8 ring-green-400" v-model="username" placeholder="At least 3 characters">

      <button @click.prevent="submit" :disabled="disabled" :class="`w-full mt-10 text-3xl font-black pointer p-5 rounded-xl transition ${
        disabled
        ? 'bg-green-400 text-green-300 cursor-not-allowed'
        : 'text-white bg-green-600 hover:bg-green-700 focus:bg-green-700 focus:outline-none cursor-pointer'
      }`">Continue <i class="ml-5 text-2xl fas fa-arrow-right"></i></button>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data: () => ({
    username: ""
  }),
  methods: {
    submit() {
      this.$store.dispatch("login", this.username);
    }
  },
  computed: {
    loading() {
      return this.$store.state.loading;
    },
    disabled() {
      return this.username.trim().length <= 3;
    }
  }
}
</script>

<style scoped>

</style>