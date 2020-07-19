select (balance.revenue - balance.expense) balance from (
select 
(select COALESCE(SUM(price),0) from "transaction" e where (EXTRACT(MONTH FROM e."date" ) = :month and EXTRACT(YEAR FROM e."date" ) = :year) and e."type" = 'EXPENSE' and e.user_id =:user_id) expense,
(select COALESCE(SUM(price),0) from "transaction" r where (EXTRACT(MONTH FROM r."date" ) = :month and EXTRACT(YEAR FROM r."date" ) = :year) and r."type" = 'REVENUE' and r.user_id =:user_id) revenue
) as balance

 
