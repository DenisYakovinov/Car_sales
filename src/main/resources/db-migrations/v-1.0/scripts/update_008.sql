CREATE TABLE advertisements (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    description VARCHAR,
    user_id BIGINT NOT NULL,
    car_model_id BIGINT NOT NULL,
    photo BYTEA,
    isSold BOOLEAN,
    created TIMESTAMP,
    release_date DATE
);

ALTER TABLE If EXISTS advertisements
    ADD CONSTRAINT fk_advertisement_to_user FOREIGN KEY (user_id) REFERENCES users(id)
    ON UPDATE CASCADE ON DELETE CASCADE,
    ADD CONSTRAINT fk_advertisement_to_cars FOREIGN KEY (car_model_id) REFERENCES car_models(id)
    ON UPDATE CASCADE ON DELETE CASCADE;