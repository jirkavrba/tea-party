<template>
  <div class="flex flex-col">
    <header class="bg-gray-800 text-center py-10 shadow-xl">
      <span class="font-black text-3xl mr-5 text-green-500">お茶会</span>
      <h1 class="font-black text-6xl text-white">
        Tea party
      </h1>
    </header>
    <div class="mt-10 container mx-auto">
      <router-view/>
    </div>
  </div>
</template>

<script>
import {defineComponent} from "vue";

export default defineComponent({
  name: "App"
})
</script>