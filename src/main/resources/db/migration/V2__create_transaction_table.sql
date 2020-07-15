CREATE TABLE "transaction" (
	id bigserial NOT NULL,
	title varchar(100) NOT NULL,
	price numeric(19,2) NOT NULL,
	user_id int8 NOT NULL,
	CONSTRAINT transaction_pk PRIMARY KEY (id),
	CONSTRAINT transaction_fk FOREIGN KEY (user_id) REFERENCES "user"(id)
);
