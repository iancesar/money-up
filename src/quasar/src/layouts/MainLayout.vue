<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar :class="toolbarColor">
        <q-toolbar-title>
          <div class="title">Demonstrativo de Pagamento</div>
          <div class="employee">Ian César Vidinha Rêgo</div>
        </q-toolbar-title>
        <q-btn flat icon="nights_stay" @click="changeTheme()" />
        <!-- <q-btn flat icon="exit_to_app" /> -->
      </q-toolbar>
    </q-header>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
import EssentialLink from "components/EssentialLink";

export default {
  name: "MainLayout",

  components: {
    EssentialLink
  },

  data() {
    return {};
  },
  computed: {
    toolbarColor() {
      if (this.$q.dark.isActive) {
        return "bg-dark";
      } else {
        return "company-gradient";
      }
    }
  },
  methods: {
    changeTheme() {
      this.$q.dark.set(!this.$q.dark.isActive);
    }
  }
};
</script>

<style scoped>
.title {
  font-size: 18px;
}
.employee {
  font-size: 12px;
}
</style>
