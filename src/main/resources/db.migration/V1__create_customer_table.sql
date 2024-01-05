CREATE TABLE customer (
                          chat_id SERIAL PRIMARY KEY,
                          customer_first_name VARCHAR(50) NOT NULL,
                          customer_last_name VARCHAR(50) NOT NULL,
                          customer_phone_number VARCHAR(20) NOT NULL,
                          register_at TIMESTAMP,
                          role VARCHAR(255)
);
