<template>
  <div class="flex flex-col">
    <header class="bg-gray-800 text-center py-10 shadow-xl">
      <span class="font-black text-3xl text-gray-700 mx-5">&laquo;</span>
      <span class="font-black text-3xl text-green-500">お茶会</span>
      <span class="font-black text-3xl text-gray-700 mx-5">&raquo;</span>
      <h1 class="font-black text-6xl text-white">
        Tea party
      </h1>
    </header>
    <div :class="`mt-10 container mx-auto transition filter ${loading ? 'blur-sm' : ''}`">
      <router-view/>
    </div>
    <div class="rounded-xl transition absolute w-full h-full left-0 top-0 flex items-center justify-center bg-opacity-75 bg-gray-800" v-if="loading">
      <div class="animate-spin w-48 h-48 flex items-center justify-center">
        <i class="fas fa-yin-yang text-9xl text-white"></i>
      </div>
    </div>
  </div>
</template>

<script>
import {defineComponent} from "vue";

export default defineComponent({
  name: "App",
  computed: {
    loading() {
      return this.$store.state.loading;
    }
  }
})
</script>