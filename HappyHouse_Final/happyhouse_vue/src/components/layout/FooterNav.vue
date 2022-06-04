<!-- @format -->

<template>
  <div tile class="white--text text-center footer mt-15">
    <v-btn v-for="icon in icons" :key="icon" class="mx-4 white--text" icon>
      <v-icon size="24px">
        {{ icon }}
      </v-icon>
    </v-btn>
    <v-card-text class="white--text pt-0">
      {{ new Date().getFullYear() }} — <strong>HappyHouse김은경성문조</strong>
    </v-card-text>
  </div>
</template>

<script>
export default {
  data: () => ({
    icons: ["mdi-facebook", "mdi-twitter", "mdi-linkedin", "mdi-instagram"],
  }),
};
</script>

<style>
.footer {
  width: 100%;
  background-color: transparent;
}
</style>
