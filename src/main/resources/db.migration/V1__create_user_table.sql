CREATE TABLE customers (
                       chat_id       BIGINT       NOT NULL PRIMARY KEY,
                       first_name    VARCHAR(255) NOT NULL,
                       last_name     VARCHAR(255) NOT NULL,
                       phone_number  VARCHAR(255) NOT NULL,
                       registered_at TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);
