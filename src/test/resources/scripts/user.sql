SET
FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE user;
TRUNCATE TABLE role;
TRUNCATE TABLE user_role;


SET
FOREIGN_KEY_CHECKS = 1;




-- USER
INSERT INTO `user` (id, `phone_number`, `first_name`, `last_name`, `gender`, `age`, `deleted`, `username`, `email`)
VALUES (1, '123456789', 'Benjamin', 'Williams', '0', 22, false, 'Benjamin123', 'benjamin100@gmail.com');

INSERT INTO `user` (id, `phone_number`, `first_name`, `last_name`, `gender`, `age`, `deleted`, `username`, `email`)
VALUES (2, '986564654', 'Anie', 'Akkerman', '1', 25, false, 'Anie009', 'anie123@gmail.com');


-- ROLE

INSERT INTO `role` (id, `name`)
VALUES (1, 'ROLE_WAITER');

INSERT INTO `role` (id, `name`)
VALUES (2, 'ROLE_MANAGER');

-- USER_ROLE
insert into user_role (user_id, role_id)
values (1, 1);

insert into user_role (user_id, role_id)
values (2, 2);