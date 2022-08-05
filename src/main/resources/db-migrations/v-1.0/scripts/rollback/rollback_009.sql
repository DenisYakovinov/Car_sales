ALTER TABLE IF EXISTS cars
    DROP CONSTRAINT fk_car_to_engine,
    DROP COLUMN engine_id,
    DROP CONSTRAINT fk_car_to_brands,
    DROP COLUMN car_brand_id,
    DROP CONSTRAINT fk_car_to_body_types,
    DROP COLUMN car_body_type_id;