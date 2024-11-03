CREATE TABLE IF NOT EXISTS thread
(thread_number INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
thread_name VARCHAR(1000) NOT NULL,
created_ymdhms VARCHAR(14) NOT NULL,
updated_ymdhms VARCHAR(14) NOT NULL);

CREATE TABLE IF NOT EXISTS message
(thread_number INT NOT NULL,
message_number INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
message VARCHAR(1000) NOT NULL,
created_ymdhms VARCHAR(14) NOT NULL,
created_name VARCHAR(100) NOT NULL DEFAULT '匿名',
updated_ymdhms VARCHAR(14) NOT NULL,
FOREIGN KEY(thread_number) REFERENCES thread(thread_number));