CREATE TABLE IF NOT EXISTS users (
    id IDENTITY PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL CHECK (role IN ('ADMIN', 'USER', 'SUPPORT'))
);

CREATE TABLE IF NOT EXISTS tickets (
    id IDENTITY PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description CLOB NOT NULL,
    priority VARCHAR(10) NOT NULL,
    category VARCHAR(20) NOT NULL,
    status VARCHAR(20) DEFAULT 'NEW' NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by BIGINT NOT NULL,
    FOREIGN KEY (created_by) REFERENCES users(id)
);

-- Insert test users
MERGE INTO users (username, password, role)
KEY(username)
VALUES ('admin', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'ADMIN');

MERGE INTO users (username, password, role)
KEY(username)
VALUES ('user', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'USER');

-- Insert test tickets
INSERT INTO tickets (title, description, priority, category, status, created_by)
SELECT 'Cannot access email',
       'Getting error when trying to open Outlook',
       'HIGH',
       'SOFTWARE',
       'NEW',
       u.id
FROM users u
WHERE u.username = 'user';

INSERT INTO tickets (title, description, priority, category, status, created_by)
SELECT 'Need new monitor',
       'Current monitor has dead pixels',
       'MEDIUM',
       'HARDWARE',
       'IN_PROGRESS',
       u.id
FROM users u
WHERE u.username = 'user'; 