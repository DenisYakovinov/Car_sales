CREATE TABLE advertisements (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    description VARCHAR,
    user_id BIGINT NOT NULL,
    car_model_id BIGINT NOT NULL,
    is_sold BOOLEAN,
    created TIMESTAMP,
    release_date DATE,
    car_mileage INTEGER,
    car_owner INTEGER,
    price INTEGER,
    engine_id BIGINT NOT NULL
);

ALTER TABLE If EXISTS advertisements
    ADD CONSTRAINT fk_advertisement_to_user FOREIGN KEY (user_id) REFERENCES users(id)
    ON UPDATE CASCADE ON DELETE CASCADE,
    ADD CONSTRAINT fk_advertisement_to_cars FOREIGN KEY (car_model_id) REFERENCES car_models(id)
    ON UPDATE CASCADE ON DELETE CASCADE,
    ADD CONSTRAINT fk_advertisement_to_engine FOREIGN KEY (engine_id) REFERENCES engine(id)
    ON UPDATE CASCADE ON DELETE CASCADE;