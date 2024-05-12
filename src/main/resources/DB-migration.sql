CREATE TABLE IF NOT EXISTS authors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS genres (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author_id INT,
    genre_id INT,
    price DECIMAL(10, 2),
    FOREIGN KEY (author_id) REFERENCES authors(id),
    FOREIGN KEY (genre_id) REFERENCES genres(id)
);

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    user_id INT,
    book_id INT,
    quantity INT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);
INSERT INTO authors (name) VALUES
('Harper Lee'),
('George Orwell'),
('Jane Austen'),
('F. Scott Fitzgerald'),
('J.D. Salinger');
INSERT INTO genres (name) VALUES
('Fiction'),
('Dystopian'),
('Romance'),
('Coming-of-age');
INSERT INTO books (title, author_id, genre_id, price) VALUES
('To Kill a Mockingbird', 1, 1, 10.99),
('1984', 2, 2, 12.49),
('Pride and Prejudice', 3, 3, 8.99),
('The Great Gatsby', 4, 1, 11.79),
('The Catcher in the Rye', 5, 4, 9.99);
INSERT INTO users (username, password) VALUES
('user1', 'password1'),
('user2', 'password2'),
('user3', 'password3'),
('user4', 'password4'),
('user5', 'password5');
-- Предположим, что пользователи и книги уже добавлены в базу данных, и у нас есть их идентификаторы
INSERT INTO orders (user_id, book_id, quantity) VALUES
(1, 1, 2),
(2, 3, 1),
(3, 2, 3),
(4, 4, 2),
(5, 5, 1);

