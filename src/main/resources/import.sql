INSERT INTO tb_movie (title, director, description, price, release) VALUES ('Inception', 'Christopher Nolan', 'A mind-bending thriller about dreams within dreams.', 19.99, '2010-07-16');
INSERT INTO tb_movie (title, director, description, price, release) VALUES ('The Dark Knight', 'Christopher Nolan', 'Batman battles the Joker in Gotham City.', 14.99, '2008-07-18');
INSERT INTO tb_movie (title, director, description, price, release) VALUES ('Interstellar', 'Christopher Nolan', 'A team of astronauts travel through a wormhole in search of a new home for humanity.', 24.99, '2014-11-07');
INSERT INTO tb_movie (title, director, description, price, release) VALUES ('The Matrix', 'Wachowski Brothers', 'A hacker learns the world he lives in is a simulation.', 12.99, '1999-03-31');
INSERT INTO tb_movie (title, director, description, price, release) VALUES ('Fight Club', 'David Fincher', 'An insomniac office worker forms an underground fight club.', 10.99, '1999-10-15');
INSERT INTO tb_movie (title, director, description, price, release) VALUES ('The Shawshank Redemption', 'Frank Darabont', 'Two imprisoned men bond over years, finding solace and eventual redemption through acts of common decency.', 9.99, '1994-09-23');
INSERT INTO tb_movie (title, director, description, price, release) VALUES ('Forrest Gump', 'Robert Zemeckis', 'The presidencies of Kennedy and Johnson, the Vietnam War, the Watergate scandal and other historical events unfold from the perspective of an Alabama man with an extraordinary life story.', 19.49, '1994-07-06');
INSERT INTO tb_movie (title, director, description, price, release) VALUES ('The Godfather', 'Francis Ford Coppola', 'The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.', 29.99, '1972-03-24');
INSERT INTO tb_movie (title, director, description, price, release) VALUES ('The Godfather: Part II', 'Francis Ford Coppola', 'The early life and career of Vito Corleone in 1920s New York is depicted, along with the story of his son Michael in the 1950s.', 24.99, '1974-12-20');
INSERT INTO tb_movie (title, director, description, price, release) VALUES ('The Lord of the Rings: The Fellowship of the Ring', 'Peter Jackson', 'A young hobbit embarks on an epic journey to destroy a powerful ring and defeat the dark lord Sauron.', 19.99, '2001-12-19');
INSERT INTO tb_movie (title, director, description, price, release) VALUES ('The Lord of the Rings: The Two Towers', 'Peter Jackson', 'Frodo and Sam continue their journey to Mount Doom, while Aragorn, Gandalf, and Legolas prepare for the battle with the forces of Sauron.', 18.99, '2002-12-18');
INSERT INTO tb_movie (title, director, description, price, release) VALUES ('The Lord of the Rings: The Return of the King', 'Peter Jackson', 'The final battle for Middle-earth begins as the forces of good fight to defeat Sauron once and for all.', 22.99, '2003-12-17');
INSERT INTO tb_movie (title, director, description, price, release) VALUES ('Star Wars: Episode IV - A New Hope', 'George Lucas', 'A young farm boy joins a rebel alliance to fight against the evil Galactic Empire and its ruthless leader, Darth Vader.', 16.99, '1977-05-25');
INSERT INTO tb_movie (title, director, description, price, release) VALUES ('Star Wars: Episode V - The Empire Strikes Back', 'Irvin Kershner', 'The rebels are pursued by Darth Vader and the Galactic Empire after the destruction of their Death Star base.', 17.49, '1980-05-21');
INSERT INTO tb_movie (title, director, description, price, release) VALUES ('Star Wars: Episode VI - Return of the Jedi', 'Richard Marquand', 'After rescuing Han Solo from Jabba the Hutt, the rebels prepare for a final battle to destroy the second Death Star.', 18.99, '1983-05-25');
INSERT INTO tb_movie (title, director, description, price, release) VALUES ('The Silence of the Lambs', 'Jonathan Demme', 'A young FBI trainee seeks the help of an imprisoned cannibalistic serial killer to catch another killer on the loose.', 13.99, '1991-02-14');
INSERT INTO tb_movie (title, director, description, price, release) VALUES ('Gladiator', 'Ridley Scott', 'A betrayed Roman general seeks revenge against the corrupt emperor who murdered his family.', 19.99, '2000-05-05');
INSERT INTO tb_movie (title, director, description, price, release) VALUES ('The Dark Knight Rises', 'Christopher Nolan', 'Eight years after the events of "The Dark Knight", Bruce Wayne comes out of retirement to stop a new threat, Bane.', 17.99, '2012-07-20');
INSERT INTO tb_movie (title, director, description, price, release) VALUES ('The Prestige', 'Christopher Nolan', 'Two magicians engage in a bitter rivalry, each trying to best the other with increasingly dangerous tricks.', 14.99, '2006-10-20');
INSERT INTO tb_movie (title, director, description, price, release) VALUES ('The Lion King', 'Roger Allers, Rob Minkoff', 'A young lion prince flees his kingdom only to learn the true meaning of responsibility and bravery.', 12.49, '1994-06-15');
INSERT INTO tb_movie (title, director, description, price, release) VALUES ('Jurassic Park', 'Steven Spielberg', 'A theme park featuring genetically resurrected dinosaurs turns into a nightmare when the creatures escape.', 15.49, '1993-06-11');
INSERT INTO tb_movie (title, director, description, price, release) VALUES ('Titanic', 'James Cameron', 'A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.', 16.99, '1997-12-19');
INSERT INTO tb_movie (title, director, description, price, release) VALUES ('Avatar', 'James Cameron', 'A paraplegic former Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.', 19.99, '2009-12-18');
INSERT INTO tb_movie (title, director, description, price, release) VALUES ('The Big Lebowski', 'Joel Coen, Ethan Coen', 'Jeff "The Dude" Lebowski is mistaken for a millionaire of the same name, leading to a series of comic misadventures.', 13.49, '1998-03-06');
INSERT INTO tb_movie (title, director, description, price, release) VALUES ('The Exorcist', 'William Friedkin', 'When a young girl is possessed by a demon, her mother seeks the help of two priests to perform an exorcism.', 16.99, '1973-12-26');


INSERT INTO tb_genre (name) VALUES ('Action');
INSERT INTO tb_genre (name) VALUES ('Thriller');
INSERT INTO tb_genre (name) VALUES ('Adventure');
INSERT INTO tb_genre (name) VALUES ('Animation');
INSERT INTO tb_genre (name) VALUES ('Drama');
INSERT INTO tb_genre (name) VALUES ('Western');
INSERT INTO tb_genre (name) VALUES ('Scientific Fiction');


INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (1, 2);
INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (1, 7);

INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (2, 1);
INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (2, 2);

INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (3, 7);
INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (3, 5);

INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (4, 7);
INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (4, 2);

INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (5, 2);
INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (5, 5);

INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (6, 5);

INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (7, 5);
INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (7, 3);

INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (8, 1);
INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (8, 5);

INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (9, 1);
INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (9, 5);

INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (10, 1);
INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (10, 3);
INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (10, 7);

INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (11, 1);
INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (11, 3);
INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (11, 7);

INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (12, 1);
INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (12, 3);
INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (12, 7);

INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (13, 1);
INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (13, 3);
INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (13, 7);

INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (14, 1);
INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (14, 3);
INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (14, 7);

INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (15, 1);
INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (15, 3);
INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (15, 7);

INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (16, 2);
INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (16, 5);

INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (17, 1);
INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (17, 5);

INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (18, 1);
INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (18, 2);

INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (19, 2);
INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (19, 5);

INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (20, 4);
INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (20, 3);

INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (21, 1);
INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (21, 3);

INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (22, 5);

INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (23, 1);
INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (23, 7);

INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (24, 5);

INSERT INTO tb_movie_genre (movie_id, genre_id) VALUES (25, 2);


INSERT INTO tb_user (name, email, password) VALUES ('Admin', 'admin@gmail.com','$2a$10$N7SkKCa3r17ga.i.dF9iy.BFUBL2n3b6Z1CWSZWi/qy7ABq/E6VpO');
INSERT INTO tb_user (name, email, password) VALUES ('User', 'user@gmail.com','$2a$10$N7SkKCa3r17ga.i.dF9iy.BFUBL2n3b6Z1CWSZWi/qy7ABq/E6VpO');
INSERT INTO tb_user (name, email, password) VALUES ('User2', 'user2@gmail.com','$2a$10$N7SkKCa3r17ga.i.dF9iy.BFUBL2n3b6Z1CWSZWi/qy7ABq/E6VpO');


INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');
INSERT INTO tb_role (authority) VALUES ('ROLE_USER');


INSERT INTO tb_user_role (user_id, role_id) VALUES (1,1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (1,2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2,2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3,2);


INSERT INTO tb_review (user_id, movie_id, review, rating) VALUES (2, 1, 'Bom filme!', 8.9);
INSERT INTO tb_review (user_id, movie_id, review, rating) VALUES (3, 1, 'Achei mais ou menos...', 6.0);


