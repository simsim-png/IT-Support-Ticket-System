-- Insert default admin user if not exists
MERGE INTO users (username, password, role)
KEY(username)
VALUES ('admin', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'ADMIN');

-- Insert default support user if not exists
MERGE INTO users (username, password, role)
KEY(username)
VALUES ('support', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'SUPPORT');

-- Insert default regular user if not exists
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