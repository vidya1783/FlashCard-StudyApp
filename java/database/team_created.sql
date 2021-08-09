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
	deck_id serial PRIMARY KEY NOT NULL,
	creator_id int REFERENCES users (user_id),
	deck_name varchar(200) NOT NULL UNIQUE,
	deck_description varchar(200)
);


CREATE TABLE flashcard_deck (
	flashcard_id SERIAL NOT NULL REFERENCES flashcard (flashcard_id),
	deck_id int NOT NULL REFERENCES deck (deck_id),
        PRIMARY KEY(flashcard_id, deck_id)
);

CREATE TABLE tag (
	tag_id SERIAL PRIMARY KEY NOT NULL,
	tag_text varchar(150) NOT NULL UNIQUE,
	creator_id int REFERENCES users (user_id)

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

-- INSERT INTO flashcard (flashcard_id, creator_id, question_text, answer_text)
-- VALUES (1000, 100, 'philadelphia','PA'),
-- (1001, 100, 'new york','NY'),
-- (1002, 100, 'picasso','painter'),
-- (1003, 100, 'rodin','sculptor'),
-- (1004, 100, 'mnesicles','architect');
INSERT INTO flashcard (flashcard_id, creator_id, question_text, answer_text)
VALUES (1000, 100, 'Who is credited as the designer of the many statues which decorated the Parthenon','Phidias'),
(1001, 100, 'What artist was struck in the face with a mallet by an envious rival, disfiguring him for life','Michelangelo'),
(1002, 100, 'Who is picasso?','A Painter'),
(1003, 100, 'Who is Rodin?','A Sculptor'),
(1004, 100, 'Who is Mnesicles','An Architect'),
(1005, 100, 'Which artist is best known for a painting of his mother','James Abbott McNeil Whistler'),
(1006, 200, 'What art movement was Yoko Ono associated with during the 1960s?','Fluxus'),
(1007, 200, 'What city was the first capital of the United States','New York City'),
(1008, 200, 'When was the Declaration of Independence signed','August 2nd, 1776'),
(1009, 200, 'Where did the pilgrims land in America','Unknown'),
(1010, 200, 'What did Paul Revere shout on his midnight ride in 1775','The regulars are coming!'),
(1011, 300, 'What is the only number that has the same number of letters as its meaning?','Four'),
(1012, 300, 'What number does not have its own Roman numeral','Zero'),
(1013, 300, 'What is the only even prime number','Two'),
(1014, 300, 'What is the smallest perfect number','Six'),
(1015, 400, 'This essential gas is important so that we can breathe','Oxygen'),
(1016, 400, 'What is the nearest planet to the sun?','Mercury'),
(1017, 400, 'How many teeth does an adult human have?','32'),
(1018, 400, 'The earth has three layers of varying temperatures. What are its three layers?','Crust, mantle, core'),
(1019, 500, 'What are peanuts?','Technically a type of bean.'),
(1020, 500, '85% of plant life is found where?','In space'),
(1021, 500, 'Where do plants get their food from?','The sun, the ground and sometimes from animals'),
(1022, 500, 'Strawberries, peaches, apples and apricots are in the same family of plants as roses. True or false?','True'),
(1023, 600, 'What singer has had a Billboard No. 1 hit in each of the four decades?','Mariah Carey'),
(1024, 600, 'What was Freddie Mercurys real name','Farrokh Bulsara'),
(1025, 600, 'Who was the very first American Idol winner','Kelly Clarkson'),
(1026, 600, 'Which member of the Avengers had a brief stint as a pop star?','Brie Larson');

INSERT INTO flashcard_deck (flashcard_id, deck_id) 
VALUES (1002, 50),
(1003, 50),
(1004, 50);

INSERT INTO tag (tag_id, tag_text, creator_id) 
VALUES (500, 'modern', 100), 
(501, 'ancient', 100),
(502, 'starts with p', 100),
(503, 'starts with n', 200);   

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