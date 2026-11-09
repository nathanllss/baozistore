CREATE TABLE client_tb
(
    id                       BIGINT AUTO_INCREMENT NOT NULL,
    name                     VARCHAR(255) NULL,
    client_since             date NULL,
    created_by_user_id       BIGINT            NULL,
    created_at               TIMESTAMP(6)           NULL,
    last_modified_at         TIMESTAMP(6)           NULL,
    last_modified_by_user_id BIGINT           NULL,
    CONSTRAINT pk_client_tb PRIMARY KEY (id)
);

CREATE TABLE order_tb
(
    id                       BIGINT AUTO_INCREMENT NOT NULL,
    cliente_id               BIGINT NULL,
    product_id               BIGINT NULL,
    quantity                 INT NULL,
    created_by_user_id       BIGINT            NULL,
    created_at               TIMESTAMP(6)           NULL,
    last_modified_at         TIMESTAMP(6)           NULL,
    last_modified_by_user_id BIGINT           NULL,
    CONSTRAINT pk_order_tb PRIMARY KEY (id)
);

CREATE TABLE product_tb
(
    id                       BIGINT AUTO_INCREMENT NOT NULL,
    name                     VARCHAR(255) NULL,
    price                    DECIMAL(19,2) NULL,
    in_stock                 BOOLEAN NULL,
    created_by_user_id       BIGINT            NULL,
    created_at               TIMESTAMP(6)           NULL,
    last_modified_at         TIMESTAMP(6)           NULL,
    last_modified_by_user_id BIGINT           NULL,
    CONSTRAINT pk_product_tb PRIMARY KEY (id)
);

ALTER TABLE order_tb
    ADD CONSTRAINT FK_ORDER_TB_ON_CLIENTE FOREIGN KEY (cliente_id) REFERENCES client_tb (id);

ALTER TABLE order_tb
    ADD CONSTRAINT FK_ORDER_TB_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product_tb (id);