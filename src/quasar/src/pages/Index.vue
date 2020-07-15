<template>
  <q-page>
    <div class="row scroll">
      <div class="col-3" v-for="month in months" v-bind:key="month.month">
        <q-card
          class="month text-center"
          :class="selectColor(month)"
          flat
          square
          @click="setMonth(month)"
        >
          <q-card-section class="month-section">
            {{ month.monthName }}
          </q-card-section>
          <q-card-section class="year-section">
            {{ month.year }}
          </q-card-section>
        </q-card>
      </div>
    </div>

    <!-- Info -->

    <q-separator spaced inset />
    <div class="info row q-gutter-md">
      <div class="col text-center">
        <q-card class="red-gradient text-white">
          <q-card-section class="first-section">Descontos</q-card-section>
          <q-card-section class="second-section text-h5">{{
            infos.discounts
          }}</q-card-section>
        </q-card>
      </div>

      <div class="col text-center">
        <q-card class="green-gradient text-white">
          <q-card-section class="first-section">Vencimentos</q-card-section>
          <q-card-section class="second-section text-h5">{{
            infos.earnings
          }}</q-card-section>
        </q-card>
      </div>
    </div>

    <div class="info liquid-info row q-gutter-md">
      <div class="col text-center">
        <q-card class="company-gradient text-white">
          <q-card-section class="first-section">Valor Liquído</q-card-section>
          <q-card-section class="second-section text-h5">{{
            infos.profits
          }}</q-card-section>
        </q-card>
      </div>
    </div>

    <q-separator spaced inset />

    <!-- Lançamentos -->
    <q-list>
      <q-item>
        <q-item-section>
          <q-item-label class="text-h5">Lançamentos</q-item-label>
        </q-item-section>
      </q-item>

      <div v-for="record in records" v-bind:key="record.id">
        <q-item>
          <q-item-section>
            <q-item-label>{{ record.title }}</q-item-label>
          </q-item-section>

          <q-item-section side>
            <q-item-label
              :class="record.debit ? 'text-negative' : 'text-positive'"
              >{{ record.value }}</q-item-label
            >
          </q-item-section>
        </q-item>
        <q-separator spaced inset />
      </div>
    </q-list>

    <!-- <div style="padding-top:70px"></div> -->

    <!-- Actions -->
    <q-tabs>
      <q-tab icon="mail" />
      <q-tab icon="print" />
    </q-tabs>

    <!-- <q-page-sticky position="bottom-right" :offset="[18, 18]">
      <q-fab color="blue-4" icon="add" direction="up">
        <q-fab-action color="blue-4" icon="mail" />
        <q-fab-action color="blue-4" icon="print" />
      </q-fab>
    </q-page-sticky>
  -->
  </q-page>
  <!-- <q-page class="flex flex-center">
    <img
      alt="Quasar logo"
      src="~assets/quasar-logo-full.svg"
    >
  </q-page> -->
</template>

<script>
export default {
  name: "PageIndex",

  data() {
    return {
      months: [
        {
          month: 1,
          monthName: "Jan",
          year: 2020
        },
        {
          month: 2,
          monthName: "Fev",
          year: 2020
        },
        {
          month: 3,
          monthName: "Mar",
          year: 2020
        },
        {
          month: 4,
          monthName: "Abr",
          year: 2020
        },
        {
          month: 5,
          monthName: "Mai",
          year: 2020
        }
      ],
      records: [],
      selectMonth: {
        month: 0,
        monthName: "",
        year: 0
      },
      infos: {
        discounts: 0,
        earnings: 0,
        profits: 0
      }
    };
  },
  methods: {
    async setMonth(month) {
      this.selectMonth = month;
      const response = await this.$axios.get(
        `/holerite/${month.month}/${month.year}`
      );
      if (response.data) {
        this.infos.profits = response.data.valorLiquido;
        this.infos.earnings = response.data.totalVencimentos;
        this.infos.discounts = response.data.totalDescontos;

        this.records = [];
        response.data.lancamentos.forEach(lanc => {
          let lancamento = {
            id: lanc.id,
            title: lanc.nome,
            value: lanc.valor,
            debit: lanc.tipo === "D" ? true : false
          };
          this.records.push(lancamento);
        });
      } else {
        this.infos.profits = "";
        this.infos.earnings = "";
        this.infos.discounts = "";
        this.records = [];
      }
    },
    selectColor(month) {
      const isSameMonth = this.selectMonth.month === month.month;

      if (isSameMonth && this.$q.dark.isActive) {
        return "bg-grey-9";
      } else if (isSameMonth) {
        return "bg-grey-4";
      } else {
        return "";
      }
    }
  }
};
</script>

<style scoped>
.scroll {
  padding-top: 16px;
  padding-bottom: 16px;
  overflow: auto;
  white-space: nowrap;
  flex-wrap: initial !important;
}
.month {
  cursor: pointer;
}
.month-section {
  padding: 16px 16px 2px 16px;
  color: gray;
}
.year-section {
  padding: 0px 16px 12px 16px;
}
.first-section {
  padding: 16px 16px 2px 16px;
}
.second-section {
  padding: 0px 16px 12px 16px;
}
.info {
  padding: 20px 12px 12px 12px;
}
.liquid-info {
  padding-top: 0px;
}
</style>
