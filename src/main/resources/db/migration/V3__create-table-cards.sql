CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE credit_card (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    card_number VARCHAR(25),
    card_valid DATE,
    cvv CHAR(3),
    card_limit NUMERIC(10,2) NOT NULL,
    remain_limit NUMERIC(10,2) NOT NULL,
    taxa_juros VARCHAR(10) NOT NULL,
    credit_password CHAR(6) NOT NULL,
    id_user UUID NOT NULL,
    FOREIGN KEY (id_user) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE debit_card (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    card_number VARCHAR(25),
    card_valid DATE,
    cvv CHAR(3),
    debit_password CHAR(4) NOT NULL,
    id_user UUID NOT NULL,
    FOREIGN KEY (id_user) REFERENCES users(id) ON DELETE CASCADE
);

