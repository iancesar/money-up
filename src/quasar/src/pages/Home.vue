<template>
  <q-page>
    <div class="column">
      <div class="col q-pb-xl gradient">
        <div class="row q-pt-lg q-pb-md text-center	">
          <div
            class="col text-h4 text-white"
            style="font-family:ComfortaaLight"
          >
            Julho
          </div>
        </div>

        <div class="row q-pt-md text-center	">
          <div
            class="col text-h3 text-white text-currency animate__animated animate__bounce"
          >
            R$ 2327,89
          </div>
        </div>
      </div>

      <div class="col">
        <transactions :transactions="transactions"></transactions>
      </div>

      <div class="col q-pa-lg"></div>
      <div class="col">
        <q-tabs v-model="tab" dense class="text-grey-9 absolute-bottom">
          <q-route-tab name="mails" icon="movie" label="Mails" to="/transaction"/>
          <q-route-tab name="alarms" icon="fas fa-plus" label="Novo" to="/transaction"/>
          <q-route-tab name="movies" icon="movie" label="Movies" to="/transaction"/>
        </q-tabs>
      </div>
    </div>
  </q-page>
</template>

<script>
import Transactions from "components/Transactions";

export default {
  data() {
    return {
      tab: "mails",
      transactions: []
    };
  },
  methods: {
    async teste() {
      try {
        const response = await this.$axios.get("/api/transaction");
        this.transactions = response.data;
        console.log(response);
      } catch (error) {
        alert(error);
      }
    }
  },
  mounted() {
    this.teste();
  },
  components: {
    transactions: Transactions
  }
};
</script>

<style lang="sass">
.transactions
    position: relative !important
    margin-top: -40px !important
.consolidated
    height: 15px
    width: 15px
    background-color: green
    border-radius: 100%
.not-consolidated
    height: 15px
    width: 15px
    background-color: red
    border-radius: 100%
</style>
