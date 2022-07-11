DO
$$
DECLARE
   c_id BIGINT;
BEGIN

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Civic', 2, 2) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('C-RV', 2, 9) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 3);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Accord', 2, 1) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Accord', 2, 3) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 3);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Freed', 2, 4) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Jazz', 2, 3) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('RAV 4', 9, 9) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 3);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 4);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('C-HR', 9, 3) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 3);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 4);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Camry', 9, 1) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Corolla', 9, 1) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Corolla', 9, 3) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Odyssey II', 2, 4) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('A4', 8, 1) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 3);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('A4', 8, 3) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 3);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('A6', 8, 1) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 3);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Q3', 8, 9) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 3);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('A3', 8, 3) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('A3', 8, 1) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('B3', 11, 1) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Golf', 11, 4) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Tiguan', 11, 9) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 3);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Passat', 11, 1) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 3);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Tuareg', 11, 9) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 3);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Ceed', 1, 3) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Ceed', 1, 1) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Ceed SW', 1, 3) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('k5', 1, 1) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('k900', 1, 1) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Sportage', 1, 9) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 3);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Sorento', 1, 9) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 3);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Dargo', 4, 9) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 3);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Jolion', 4, 9) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 3);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('F7', 4, 9) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 3);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('F7X', 4, 9) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 3);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('H9', 4, 9) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 3);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('GVM POER', 4, 6) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 3);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('GVM Wingle 7', 4, 6) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 3);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('GVM Wingle 7', 4, 6) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 3);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Jimny', 12, 9) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('SX4', 12, 3) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('SX4', 12, 1) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Vitara', 12, 9) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Wagon R', 12, 4) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 3);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Elantra', 5, 1) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Creta', 5, 9) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Tuscon', 5, 9) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 3);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Astra H', 7, 1) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Astra H', 7, 3) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Meriva', 7, 5) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Vectra', 7, 1) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('Mokka', 7, 9) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('s-90', 10, 1) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('XC-90', 10, 9) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 3);

INSERT INTO car_models (name, car_brand_id, car_body_type_id) VALUES ('XC-40', 10, 9) RETURNING id INTO c_id;
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 1);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 2);
INSERT INTO link_cars_engines (car_model_id, engine_id) VALUES (c_id, 3);

END;
$$
