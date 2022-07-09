ALTER TABLE IF EXISTS cars_engines DROP CONSTRAINT fk_engine_id;
ALTER TABLE IF EXISTS cars_engines DROP CONSTRAINT fk_car_model_id;
DROP TABLE cars_engines;