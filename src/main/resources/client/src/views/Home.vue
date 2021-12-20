<template>
  <div class="text-center mt-16">
    <h1>Redirecting...</h1>
  </div>
</template>

<script>

  export default {
    name: 'Home',
    async mounted() {
      await this.$store.dispatch("validateToken")
    }
  }
</script>
