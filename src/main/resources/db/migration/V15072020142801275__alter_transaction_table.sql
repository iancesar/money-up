UPDATE "transaction" SET "type" = 'EXPENSE' WHERE "type" IS NULL;
ALTER TABLE "transaction" ALTER COLUMN "type" SET NOT NULL;