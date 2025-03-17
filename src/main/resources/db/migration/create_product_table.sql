CREATE TABLE product
(
    id           UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
    external_id  UUID         NOT NULL,
    store_id     UUID         NOT NULL,
    name         VARCHAR(255) NOT NULL,
    price_net    DECIMAL      NOT NULL,
    vat_value    DECIMAL      NOT NULL,
    version_date TIMESTAMPTZ  NOT NULL DEFAULT now()
);