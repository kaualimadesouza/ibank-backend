CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE transfers (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    date_transfer TIMESTAMP NOT NULL,
    type SMALLINT NOT NULL,
    value_transfer NUMERIC(10,2) NOT NULL,
    id_sender UUID NOT NULL,
    FOREIGN KEY (id_sender) REFERENCES users(id) ON DELETE CASCADE,
    id_receiver UUID NOT NULL,
    FOREIGN KEY (id_receiver) REFERENCES users(id) ON DELETE CASCADE
);