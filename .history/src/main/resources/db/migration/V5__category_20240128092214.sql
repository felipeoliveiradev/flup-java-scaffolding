CREATE TABLE category (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(2000) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    deleted_at DATETIME(6) NULL
    )