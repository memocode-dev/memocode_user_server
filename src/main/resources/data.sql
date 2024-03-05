DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id CHAR(36) NOT NULL PRIMARY KEY,
                       username VARCHAR(255) UNIQUE NOT NULL,
                       nickname VARCHAR(255) NOT NULL,
                       account_id CHAR(36) NOT NULL,
                       created_at DATETIME NOT NULL,
                       updated_at DATETIME NOT NULL,
                       deleted_at DATETIME,
                       is_deleted BOOLEAN NOT NULL
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;