ALTER TABLE If EXISTS advertisements DROP CONSTRAINT fk_advertisement_to_user;
ALTER TABLE If EXISTS advertisements DROP CONSTRAINT fk_fk_advertisement_to_cars;
DROP TABLE advertisements;