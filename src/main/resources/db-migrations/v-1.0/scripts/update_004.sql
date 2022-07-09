CREATE TABLE IF NOT EXISTS cars_engines (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    engine_id BIGINT NOT NULL,
    car_model_id BIGINT NOT NULL
);

ALTER TABLE IF EXISTS cars_engines
    ADD CONSTRAINT fk_engine_id FOREIGN KEY (engine_id) REFERENCES engine(id),
    ADD CONSTRAINT fk_car_model_id FOREIGN KEY (car_model_id) REFERENCES car_models(id);
