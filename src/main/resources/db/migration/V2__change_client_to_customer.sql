ALTER TABLE client_tb RENAME TO customer_tb;

ALTER TABLE customer_tb RENAME COLUMN client_since TO customer_since;

ALTER TABLE order_tb RENAME COLUMN cliente_id TO customer_id;