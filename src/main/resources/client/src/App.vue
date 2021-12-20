<template>
  <v-app>
    <v-app-bar app color="black" dark>
      <v-spacer></v-spacer>
      <v-icon size="48">mdi-tea</v-icon>
      <v-spacer></v-spacer>
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
};
</script>
