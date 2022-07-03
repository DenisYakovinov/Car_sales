CREATE TABLE advertisements (
    id SERIAL PRIMARY KEY,
    description VARCHAR,
    user_id BIGINT NOT NULL,
    car_id BIGINT NOT NULL,
    photo BYTEA,
    isSold BOOLEAN,
    created TIMESTAMP
);

ALTER TABLE If EXISTS advertisements
    ADD CONSTRAINT fk_advertisement_to_user FOREIGN KEY (user_id) REFERENCES users(id)
    ON UPDATE CASCADE ON DELETE CASCADE,
    ADD CONSTRAINT fk_advertisement_to_cars FOREIGN KEY (car_id) REFERENCES cars(id)
    ON UPDATE CASCADE ON DELETE CASCADE;