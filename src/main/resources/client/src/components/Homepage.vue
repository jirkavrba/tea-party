<template>
  <div>
    Redirecting...
  </div>
</template>

<script>
import {defineComponent} from "vue";

export default defineComponent({
  name: "Homepage",
  async mounted() {
    await this.$store.dispatch("validateToken");
    const route = this.$store.state.token == null ? "/login" : "/lobbies";

    await this.$router.replace(route);
  }
})
</script>