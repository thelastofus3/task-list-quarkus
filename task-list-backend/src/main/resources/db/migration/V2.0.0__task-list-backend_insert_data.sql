insert into user (username, email, password, role)
VALUES ('user2', 'user2@gmail.com', '$2a$10$f25gvDQxHHvxQ7wbON4Lp.l7Ler9WgBeuKGZAjjBlCDLAwb2MK5k2', 'ROLE_USER');
insert into task (title, description, user_id, created_at, status)
VALUES ('Write a book', 'Write a book about magic city', 1, CURRENT_TIMESTAMP, 'TODO');