ALTER TABLE participations
    ADD counts_toward_total BOOLEAN;

ALTER TABLE participations
    ALTER COLUMN counts_toward_total SET NOT NULL;