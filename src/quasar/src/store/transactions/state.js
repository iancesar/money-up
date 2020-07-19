import moment from "moment";

export default function() {
  return {
    transactions: [],
    year: moment().format("YYYY"),
    month: moment().format("MMMM"),
    nome: 'ian'
  };
}
