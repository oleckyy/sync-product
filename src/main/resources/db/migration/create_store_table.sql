CREATE TABLE store
(
    id           UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
    external_id  UUID         NOT NULL,
    active       BOOLEAN      NOT NULL,
    name         VARCHAR(255) NOT NULL,
    location     VARCHAR(255) NOT NULL,
    api_url      VARCHAR(255) NOT NULL,
    version_date TIMESTAMPTZ  NOT NULL DEFAULT now()
);