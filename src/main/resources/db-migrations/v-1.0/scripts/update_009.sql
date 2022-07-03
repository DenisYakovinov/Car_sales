ALTER TABLE IF EXISTS cars
    ADD COLUMN engine_id BIGINT,
    ADD CONSTRAINT fk_car_to_engine FOREIGN KEY (engine_id) REFERENCES engine(id) ON DELETE CASCADE,
    ADD COLUMN car_brand_id BIGINT,
    ADD CONSTRAINT fk_car_to_brands FOREIGN KEY (car_brand_id) REFERENCES car_brands(id) ON DELETE CASCADE,
    ADD COLUMN car_body_type_id BIGINT,
    ADD CONSTRAINT fk_car_to_body_types FOREIGN KEY (car_body_type_id) REFERENCES car_body_types(id) ON DELETE CASCADE;