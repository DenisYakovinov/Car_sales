CREATE TABLE IF NOT EXISTS history_owner (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    driver_id BIGINT NOT NULL,
    car_id BIGINT NOT NULL
);

ALTER TABLE IF EXISTS history_owner
    ADD CONSTRAINT fk_driver_id FOREIGN KEY (driver_id) REFERENCES drivers(id),
    ADD CONSTRAINT fk_car_id FOREIGN KEY (car_id) REFERENCES cars(id);
