BEGIN TRANSACTION;
DROP TABLE IF EXISTS flashcard CASCADE;
DROP TABLE IF EXISTS flashcard_search_text CASCADE;
DROP TABLE IF EXISTS deck CASCADE;
DROP TABLE IF EXISTS flashcard_deck CASCADE;
DROP TABLE IF EXISTS tag CASCADE;
DROP TABLE IF EXISTS flashcard_tag CASCADE;


CREATE TABLE flashcard (
	flashcard_id SERIAL NOT NULL PRIMARY KEY,
	question_text VARCHAR(200) NOT NULL,
	answer_text VARCHAR(200) NOT NULL,

        creator_id int REFERENCES users (user_id)
);

CREATE TABLE flashcard_search_text (
	flashcard_id int  NOT NULL PRIMARY KEY,
	keywords text NOT NULL
);


CREATE TABLE deck (
	deck_id SERIAL PRIMARY KEY NOT NULL, 
	creator_id int REFERENCES users (user_id),
	deck_name varchar(200) NOT NULL,
	deck_description varchar(200)
);


CREATE TABLE flashcard_deck (
	flashcard_id SERIAL NOT NULL REFERENCES flashcard (flashcard_id),
	deck_id int NOT NULL REFERENCES deck (deck_id),
        PRIMARY KEY(flashcard_id, deck_id)
);

CREATE TABLE tag (
	tag_id SERIAL PRIMARY KEY NOT NULL,
	tag_text varchar(150) NOT NULL UNIQUE
);


CREATE TABLE flashcard_tag (
	flashcard_id int  NOT NULL,
	tag_id int NOT NULL,
        PRIMARY KEY (flashcard_id, tag_id)
);

/* and they all use 'mypassword' */
INSERT INTO users (user_id, username, password_hash, role) VALUES
(100, 'user100','$2a$10$RJJxim1wZ65TzWYZMGiWcO.4lGSWYg9eKJ7wcli5IKcPLic66TMKG','ROLE_USER'),
(200, 'user200','$2a$10$RJJxim1wZ65TzWYZMGiWcO.4lGSWYg9eKJ7wcli5IKcPLic66TMKG','ROLE_USER'),
(300, 'user300','$2a$10$RJJxim1wZ65TzWYZMGiWcO.4lGSWYg9eKJ7wcli5IKcPLic66TMKG','ROLE_USER'),
(400, 'user400','$2a$10$RJJxim1wZ65TzWYZMGiWcO.4lGSWYg9eKJ7wcli5IKcPLic66TMKG','ROLE_USER'),
(500, 'user500','$2a$10$RJJxim1wZ65TzWYZMGiWcO.4lGSWYg9eKJ7wcli5IKcPLic66TMKG','ROLE_USER'),
(600, 'user600','$2a$10$RJJxim1wZ65TzWYZMGiWcO.4lGSWYg9eKJ7wcli5IKcPLic66TMKG','ROLE_USER');

INSERT INTO deck (deck_id, deck_name, creator_id, deck_description)
VALUES (50, 'Art',100, 'flashcards that relate to art'),
 (51, 'History',200, 'flashcards that relate to history'),
(52, 'Math',300, 'flashcards that relate to math'),
 (53, 'Science',400, 'flashcards that relate to science'),
(54, 'Plants',500, 'flashcards that relate to plants'),
(55, 'Music',600, 'flashcards that relate to music');

INSERT INTO flashcard (flashcard_id, creator_id, question_text, answer_text)
VALUES (1000, 100, 'philadelphia','PA'),
(1001, 100, 'new york','NY'),
(1002, 100, 'picasso','painter'),
(1003, 100, 'rodin','sculptor'),
(1004, 100, 'mnesicles','architect');

INSERT INTO flashcard_deck (flashcard_id, deck_id) 
VALUES (1002, 50),
(1003, 50),
(1004, 50);

INSERT INTO tag (tag_id, tag_text) 
VALUES (500, 'modern'), 
(501, 'ancient'),
(502, 'starts with p'),
(503, 'starts with n');   

INSERT INTO flashcard_search_text (flashcard_id, keywords)
VALUES (1000, 'brotherly love'),
(1001, 'big apple'),
(1002, 'abstract'),
(1003, 'thinker'),
(1004, 'boring');

INSERT INTO flashcard_tag (flashcard_id, tag_id)
VALUES (1000, 502),
(1002, 502), 
(1002, 500),
(1003, 500),
(1004, 501);


SELECT * FROM users;
SELECT * FROM flashcard;
SELECT * FROM flashcard_search_text;
SELECT * FROM flashcard_deck;
SELECT * FROM flashcard_tag;
SELECT * FROM deck;
SELECT * FROM tag; 

COMMIT TRANSACTION;