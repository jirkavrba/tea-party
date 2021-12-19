<template>
  <v-app>
    <v-app-bar color="black">
      <v-btn icon="mdi-github" href="https://github.com/jirkavrba/tea-party" target="_blank" color="transparent" text-color="white"></v-btn>
      <v-spacer/>
      <v-app-bar-title class="text-white">
        <v-icon class="mr-4">mdi-tea</v-icon>
        <strong>Tea party</strong>
      </v-app-bar-title>
      <v-spacer/>
    </v-app-bar>
    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: 'App',
  data: () => ({
    //
  }),
}
</script>
