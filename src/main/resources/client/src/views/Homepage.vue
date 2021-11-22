<template>
  <div class="text-center text-white">Redirecting...</div>
</template>
<script>
import {defineComponent} from "vue";

export default defineComponent({
  name: "Homepage",
  mounted() {
    this.$router.push(this.$store.state.token === null ? "/login" : "/lobbies");
  }
});
</script>