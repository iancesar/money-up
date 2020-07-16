CREATE TYPE transaction_type AS ENUM ('EXPENSE', 'REVENUE');

ALTER TABLE "transaction" ADD COLUMN "type" transaction_type;