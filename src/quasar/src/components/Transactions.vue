<template>
  <q-card class="transactions transaction-card" square flat>
    <q-card-section class="q-mt-md">
      <div class="q-mt-md">
        <span class="text-h6" style="font-family:ComfortaaLight"
          >Transações</span
        >
      </div>
      <q-list
        v-for="transaction in transactions"
        v-bind:key="transaction.id"
        class="animate__animated animate__fadeInUpBig"
      >
        <q-slide-item
          class="q-mt-md"
          left-color="red-8"
          @left="deleteTransaction(transaction)"
        >
          <template v-slot:left>
            <q-icon name="fas fa-trash" />
          </template>

          <q-item clickable v-ripple>
            <q-item-section>
              <q-item-label
                class=""
                style="font-size:17px;font-family:ComfortaaLight"
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
        </q-slide-item>
      </q-list>
    </q-card-section>
  </q-card>
</template>

<script>
import moment from "moment";

export default {
  data() {
    return {};
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
    transactionDate: {
      get() {
        return this.$store.state.transactions.transactionDate;
      },
      set(val) {
        this.$store.commit("transactions/setTransactionDate", val);
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
    },

    async deleteTransaction(transaction) {
      try {
        await this.$axios.delete(`/api/transaction/${transaction.id}`);

        const month = moment(this.month, "MMMM").format("MM");

        const response = await this.$axios.get(
          `/api/transaction/${this.year}/${month}/balance`
        );
        this.balance = response.data.balanceFormatted;
        this.list();
      } catch (error) {
        console.log(error);
      }
    },
    async list() {
      try {
        const month = moment(this.month, "MMMM").format("MM");
        const response = await this.$axios.get(
          `/api/transaction/${this.year}/${month}`
        );

        this.transactions = response.data;
      } catch (error) {
        alert(error);
      }
    }
  },
  watch: {
    month(val) {
      this.list();
    }
  },
  mounted() {
    this.list();
  }
};
</script>
