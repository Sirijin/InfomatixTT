INSERT INTO author (id, name)
VALUES (1, 'Leo Tolstoy'),
       (2, 'Fyodor Dostoevsky'),
       (3, 'Anton Chekhov');

INSERT INTO publisher (id, name, city)
VALUES (1, 'Moscow Publishing House', 'Moscow'),
       (2, 'St. Petersburg Press', 'St. Petersburg'),
       (3, 'Theater Publishers', 'Moscow');

INSERT INTO book (id, title, year_of_publishing, publisher_id)
VALUES (1, 'War and Peace', 1869, 1),
       (2, 'Anna Karenina', 1877, 1),
       (3, 'Crime and Punishment', 1866, 2),
       (4, 'The Brothers Karamazov', 1880, 2),
       (5, 'The Cherry Orchard', 1904, 3),
       (6, 'The Seagull', 1896, 3);

INSERT INTO author_book (author_id, book_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (2, 4),
       (3, 5),
       (3, 6);
