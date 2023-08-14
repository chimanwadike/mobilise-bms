INSERT INTO `authors` (`first_name`, `last_name`, `email_address`) VALUES ('John', 'Doe', 'john.doe@example.com'),
('Jane', 'Smith', 'jane.smith@example.com'),
('Michael', 'Johnson', 'michael.johnson@example.com'),
('Emily', 'Brown', 'emily.brown@example.com'),
('David', 'Williams', 'david.williams@example.com');

INSERT INTO `genres` (`name`) VALUES ('Science Fiction'),
('Mystery'),
('Fantasy'),
('Romance'),
('Thriller');

INSERT INTO `books` (`title`, `isbn`, `publish_year`, `publisher`, `edition`, `pages`)  VALUES ('The Shadow of the Crescent Moon', '9780385348433', 2013, 'Random House', '1st Edition', 240),
('The Lost Symbol', '9781400079148', 2009, 'Doubleday', '1st Edition', 528),
('Brave New World', '9780060850524', 1932, 'Harper & Brothers', '1st Edition', 288),
('To Kill a Mockingbird', '9780061120084', 1960, 'Harper Lee', '1st Edition', 324),
('1984', '9780451524935', 1949, 'Secker & Warburg', '1st Edition', 328),
('Pride and Prejudice', '9780486284736', 1813, 'T. Egerton', '1st Edition', 288),
('The Hobbit', '9780547928227', 1937, 'Allen & Unwin', '1st Edition', 304),
('Harry Potter and the Philosopher Stone', '9780747532699', 1997, 'Bloomsbury', '1st Edition', 223),
('The Great Gatsby', '9780743273565', 1925, 'Scribners', '1st Edition', 180),
('The Catcher in the Rye', '9780316769488', 1951, 'Little, Brown and Company', '1st Edition', 224),
('Lord of the Rings: The Fellowship of the Ring', '9780618346257', 1954, 'Allen & Unwin', '1st Edition', 423),
('To the Lighthouse', '9780156907392', 1927, 'Hogarth Press', '1st Edition', 209),
('A Tale of Two Cities', '9780451526564', 1859, 'Chapman & Hall', '1st Edition', 416),
('Moby-Dick', '9781853260087', 1851, 'Richard Bentley', '1st Edition', 752),
('The Alchemist', '9780061122415', 1988, 'HarperOne', '1st Edition', 197),
('Alice Adventures in Wonderland', '9780141439761', 1865, 'Macmillan', '1st Edition', 96),
('Crime and Punishment', '9780486415871', 1866, 'The Russian Messenger', '1st Edition', 448),
('The Picture of Dorian Gray', '9780140437842', 1890, 'Ward, Lock and Company', '1st Edition', 214),
('One Hundred Years of Solitude', '9780060883287', 1967, 'Harper & Row', '1st Edition', 417),
('Anna Karenina', '9780198701149', 1877, 'The Russian Messenger', '1st Edition', 864),
('War and Peace', '9781853260629', 1869, 'The Russian Messenger', '1st Edition', 1225);

INSERT INTO `book_author` (`book_id`, `author_id`) VALUES (1, 1), (2, 2), (3, 3), (4, 4), (5, 5),
(6, 1), (7, 2), (8, 3), (9, 4), (10, 5),
(11, 1), (12, 2), (13, 3), (14, 4), (15, 5),
(16, 1), (17, 2), (18, 3), (19, 4), (20, 5);

INSERT INTO `book_genre` (`book_id`, `genre_id`) VALUES (1, 1), (1, 3), (2, 2), (2, 4), (3, 3),
(4, 1), (4, 5), (5, 2), (6, 1), (7, 4),
(8, 2), (9, 1), (10, 3), (11, 2), (11, 5),
(12, 1), (13, 4), (14, 3), (15, 2), (16, 1),
(17, 5), (18, 4), (19, 3), (20, 2), (20, 5);