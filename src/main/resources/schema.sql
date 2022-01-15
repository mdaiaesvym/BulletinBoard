CREATE TABLE threads
(thread_number INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
thread_name VARCHAR(1000) NOT NULL,
created_at datetime NOT NULL,
updated_at datetime NOT NULL);

CREATE TABLE messages
(thread_number INT NOT NULL,
message_number INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
message VARCHAR(1000) NOT NULL,
created_at datetime NOT NULL,
updated_at datetime NOT NULL),
FOREIGN KEY(thread_number) REFERENCES threads(thread_number);