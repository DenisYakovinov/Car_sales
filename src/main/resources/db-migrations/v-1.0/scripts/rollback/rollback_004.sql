ALTER TABLE IF EXISTS link_cars_engines DROP CONSTRAINT fk_engine_id;
ALTER TABLE IF EXISTS link_cars_engines DROP CONSTRAINT fk_car_model_id;
DROP TABLE link_cars_engines;