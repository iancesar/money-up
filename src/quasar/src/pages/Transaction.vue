<template>
  <q-page>
    <div class="column">
      <div class="col q-pb-xl" :class="'bg-' + color">
        <div
          class="col text-h4 text-white text-center q-pt-xl animate__animated animate__bounce"
          style="font-family:ComfortaaLight"
        >
          Nova {{ title }}
        </div>
      </div>

      <q-form @submit="save()" ref="form">
        <div class="col">
          <q-card class="transactions transaction-card" square flat>
            <q-card-section class="q-mt-md">
              <div class="column q-pt-md q-gutter-lg">
                <q-input
                  v-model="transaction.title"
                  label="Título"
                  :color="color"
                  lazy-rules
                  :rules="[val => !!val || 'Campo obrigatório']"
                />
                <q-input
                  v-model="transaction.price"
                  mask="R$ #,##"
                  fill-mask="0"
                  reverse-fill-mask
                  label="Preço"
                  :color="color"
                  :rules="[val => !!val || 'Campo obrigatório']"
                >
                </q-input>
                <q-input
                  v-model="transaction.date"
                  label="Data"
                  :color="color"
                  :rules="[val => !!val || 'Campo obrigatório']"
                >
                  <template v-slot:append>
                    <q-icon name="event" class="cursor-pointer">
                      <q-popup-proxy
                        ref="qDateProxy"
                        transition-show="scale"
                        transition-hide="scale"
                      >
                        <q-date
                          :color="color"
                          mask="DD/MM/YYYY"
                          v-model="transaction.date"
                          @input="() => $refs.qDateProxy.hide()"
                        />
                      </q-popup-proxy>
                    </q-icon>
                  </template>
                </q-input>
                <q-input
                  bottom-slots
                  v-model="transaction.repeatTimes"
                  label="Repetir mensalmente"
                  :color="color"
                >
                  <template v-slot:hint> Total: {{ sum }} </template>
                </q-input>

                <span class="q-field__label">Consolidada</span>
                <q-btn-toggle
                  v-model="transaction.consolidated"
                  spread
                  no-caps
                  :toggle-color="color"
                  color="white"
                  :text-color="color"
                  :options="[
                    { label: 'SIM', value: true },
                    { label: 'NÃO', value: false }
                  ]"
                />
              </div>
            </q-card-section>

            <div class="col q-pa-lg"></div>

            <q-card-actions align="around" class="fixed-bottom">
              <q-btn flat color="grey-9" to="/">Cancelar</q-btn>
              <q-btn flat :color="color" type="submit">Salvar</q-btn>
            </q-card-actions>
          </q-card>
        </div>
      </q-form>
    </div>
  </q-page>
</template>

<script>
import moment from "moment";
import accounting from "accounting";

export default {
  data() {
    return {
      transaction: {
        title: null,
        price: null,
        date: moment().format("DD/MM/YYYY"),
        consolidated: false,
        repeatTimes: null
      }
    };
  },
  computed: {
    sum() {
      if (this.transaction.repeatTimes !== null) {
        const sum =
          accounting.unformat(this.transaction.price) *
          this.transaction.repeatTimes;
        return accounting.formatMoney(sum);
      }
    },
    color() {
      if (this.isExpense) {
        return "red-8";
      } else {
        return "green-8";
      }
    },
    title() {
      if (this.isExpense) {
        return "Despesa";
      } else {
        return "Receita";
      }
    },
    isExpense() {
      return this.$route.params.type === "expense";
    }
  },
  methods: {
    async save() {
      try {
        const formattedDate = moment(
          this.transaction.date,
          "DD/MM/YYYY"
        ).format("YYYY-MM-DD");

        const type = this.isExpense ? "EXPENSE" : "REVENUE";

        const request = {
          title: this.transaction.title,
          price: accounting.unformat(this.transaction.price),
          date: formattedDate,
          consolidated: this.transaction.consolidated,
          repeatTimes: this.transaction.repeatTimes,
          type: type
        };

        await this.$axios.post("/api/transaction", request);
        this.clear();
      } catch (error) {
        console.log(error.response.data);
      }
    },
    clear() {
      this.transaction.title = null;
      this.transaction.price = null;
      this.transaction.consolidated = false;
      this.transaction.repeatTimes = null;
      this.$refs.form.resetValidation();
    }
  },
  mounted() {
    accounting.settings.currency.symbol = "R$ ";
    accounting.settings.number.decimal = ",";
  }
};
</script>

<style lang="sass">
.transactions
    position: relative !important
    margin-top: -40px !important
</style>
