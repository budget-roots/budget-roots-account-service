CREATE TABLE "account"
(
    id          UUID NOT NULL,
    version     BIGINT NOT NULL,
    keycloak_id TEXT,
    email       TEXT,
    first_name  TEXT,
    last_name   TEXT,
    CONSTRAINT pk_user PRIMARY KEY (id)
);