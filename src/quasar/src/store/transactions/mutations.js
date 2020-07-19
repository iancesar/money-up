export function setTransactions(state, transactions) {
  state.transactions = transactions;
}

export function setYear(state, year) {
  state.year = year;
}

export function setMonth(state, month) {
  state.month = month;
}

export function setNome(state, nome) {
  console.log("passando no mutation de nome ", nome);

  state.nome = nome;
}
