<template>
  <q-card class="transactions transaction-card" square flat>
    <q-card-section class="q-mt-md">
      <div class="q-mt-md">
        <span class="text-default text-weight-bolder text-h6">TRANSAÇÕES</span>
      </div>
      <q-list
        v-for="transaction in transactions"
        v-bind:key="transaction.id"
        class="animate__animated animate__fadeInUpBig"
      >
        <q-item clickable v-ripple class="q-mt-md">
          <q-item-section>
            <q-item-label
              class="text-default text-weight-bolder"
              style="font-size:17px"
              :style="getTextDecoration(transaction)"
              >{{ transaction.title }}</q-item-label
            >
            <q-item-label caption lines="1">{{
              transaction.date
            }}</q-item-label>
          </q-item-section>
          <q-item-section
            side
            class="text-currency text-weight-bolder"
            :class="getPriceClass(transaction.type)"
            style="font-size:18px"
            >{{ transaction.priceFormatted }}</q-item-section
          >
        </q-item>
      </q-list>
    </q-card-section>
  </q-card>
</template>

<script>
export default {
  // name: 'ComponentName',
  props: ["transactions"],
  data() {
    return {};
  },
  methods: {
    getPriceClass(transaction) {
      if (transaction === "EXPENSE") {
        return "text-red";
      } else {
        return "text-green";
      }
    },

    getConsolidatedClass(transaction) {
      if (transaction.consolidated) {
        return "consolidated";
      } else {
        return "not-consolidated";
      }
    },

    getTextDecoration(transaction) {
      if (transaction.consolidated) {
        return "text-decoration:line-through";
      }
    }
  }
};
</script>
