DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS outbox;

CREATE TABLE users
(
    id         CHAR(36)            NOT NULL PRIMARY KEY,
    username   VARCHAR(255) UNIQUE NOT NULL,
    nickname   VARCHAR(255)        NOT NULL,
    created_at DATETIME            NOT NULL,
    updated_at DATETIME            NOT NULL,
    deleted_at DATETIME,
    is_deleted BOOLEAN             NOT NULL
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE outbox
(
    id             CHAR(36) PRIMARY KEY,
    aggregate_id   CHAR(36) NOT NULL,
    aggregate_type ENUM('USER') NOT NULL,
    event_type     ENUM('USER_CREATED') NOT NULL,
    payload        JSON     NOT NULL,
    created_at     DATETIME NOT NULL,
    updated_at     DATETIME NOT NULL
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;