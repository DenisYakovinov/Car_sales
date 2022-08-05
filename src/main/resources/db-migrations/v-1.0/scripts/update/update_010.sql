CREATE TABLE IF NOT EXISTS photos (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    photo BYTEA,
    advertisement_id BIGINT
);

ALTER TABLE photos ADD CONSTRAINT fk_advertisement_id FOREIGN KEY (advertisement_id) REFERENCES advertisements(id)
ON DELETE CASCADE ON UPDATE CASCADE;