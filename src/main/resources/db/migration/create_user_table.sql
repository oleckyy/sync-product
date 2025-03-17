CREATE TABLE user
(
    id           UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
    active       BOOLEAN      NOT NULL,
    login        VARCHAR(255) NOT NULL,
    password     VARCHAR(255) NOT NULL,
    privilege    INTEGER      NOT NULL,
    version_date TIMESTAMPTZ  NOT NULL DEFAULT now()
);