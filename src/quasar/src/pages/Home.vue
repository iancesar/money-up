<template>
  <q-page>
    <div class="column">
      <div class="col q-pb-xl bg-grey-10" v-touch-swipe.mouse="handleSwipe">
        <div
          id="teste"
          class="row q-pt-lg q-pb-md text-center	animate__animated animate__bounce"
        >
          <!-- :class="animation" -->
          <div
            class="col-12 text-h3 text-white"
            style="font-family:ComfortaaLight"
          >
            {{ month }}
          </div>
          <div
            class="col-12 text-h6 text-white"
            style="font-family:ComfortaaLight"
          >
            {{ year }}
          </div>
        </div>

        <div class="row q-pt-xs text-center	">
          <div
            class="col text-h3 text-white text-currency animate__animated animate__bounce"
          >
            <!-- :class="animation" -->
            {{ balance }}
          </div>
        </div>
      </div>

      <div class="col">
        <transactions></transactions>
      </div>

      <div class="col q-pa-lg"></div>
      <!-- <div class="col">
        <q-tabs v-model="tab" dense class="absolute-bottom">

          <q-route-tab
            name="alarms"
            icon="fas fa-minus"
            label="Nova Despesa"
            to="/transaction/expense"
            class="text-red-9"
          />

          <q-route-tab
            name="alarms"
            icon="fas fa-plus"
            label="Nova Receita"
            to="/transaction/revenue"
            class="text-green-9"
          />

        </q-tabs>
      </div> -->
    </div>
    <q-page-sticky position="bottom-right" :offset="[18, 18]">
      <!-- <q-btn fab icon="add" color="grey-10" /> -->
      <q-fab color="grey-10" icon="add" direction="up">
        <q-fab-action color="red" icon="remove" to="/transaction/expense" />
        <q-fab-action color="green" icon="add" to="/transaction/revenue" />
      </q-fab>
    </q-page-sticky>
  </q-page>
</template>

<script>
import Transactions from "components/Transactions";
import moment, { months } from "moment";
export default {
  data() {
    return {
      tab: "mails"
    };
  },
  computed: {
    transactions: {
      get() {
        return this.$store.state.transactions.transactions;
      },
      set(val) {
        this.$store.commit("transactions/setTransactions", val);
      }
    },
    balance: {
      get() {
        return this.$store.state.balance.balance;
      },
      set(val) {
        this.$store.commit("balance/setBalance", val);
      }
    },
    month: {
      get() {
        return this.$store.state.transactions.month;
      },
      set(val) {
        this.$store.commit("transactions/setMonth", val);
      }
    },
    year: {
      get() {
        return this.$store.state.transactions.year;
      },
      set(val) {
        this.$store.commit("transactions/setYear", val);
      }
    }
  },
  methods: {
    async handleSwipe({ evt, ...info }) {
      const momentAux = moment(this.year + this.month, "YYYYMMMM");

      if (info.direction === "left") {
        const plusMonth = momentAux.add(1, "month");
        this.month = plusMonth.format("MMMM");
        this.year = plusMonth.format("YYYY");
      } else if (info.direction === "right") {
        const subtractMonth = momentAux.subtract(1, "month");
        this.month = subtractMonth.format("MMMM");
        this.year = subtractMonth.format("YYYY");
      }

      await this.getMonthBalance();
    },
    async getMonthBalance() {
      try {
        const month = moment(this.month, "MMMM").format("MM");

        const response = await this.$axios.get(
          `/api/transaction/${this.year}/${month}/balance`
        );
        this.balance = response.data.balanceFormatted;
      } catch (error) {
        console.log(error);
      }
    }
  },
  async mounted() {
    this.getMonthBalance();
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
