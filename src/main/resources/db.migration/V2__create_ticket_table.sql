CREATE TABLE ticket (
                        ticket_id SERIAL PRIMARY KEY,
                        customer_id BIGINT,
                        status VARCHAR(255),
                        date_of_visit TIMESTAMP UNIQUE,
                        FOREIGN KEY (customer_id) REFERENCES customer(chat_id) ON DELETE CASCADE
);
