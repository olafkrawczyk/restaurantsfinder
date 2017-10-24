insert into address (city, postal_code, street, id) values ('Wroclaw', '50-363', 'Rynek 6', 1);
insert into address (city, postal_code, street, id) values ('Warszawa', '00-001', 'Rynek 3', 2);
insert into address (city, postal_code, street, id) values ('Katowice', '40-201', 'Rynek 2', 3);

insert into owner (address_id, email_address, first_name, last_name, id, password) values (1 , 'john.doe@gmail.com', 'John', 'Doe', 1, 'password');
insert into owner (address_id, email_address, first_name, last_name, id, password) values (2 , 'jan.kowalski@gmail.com', 'Jan', 'Kowalski', 2, 'password');

insert into client (address_id, email_address, first_name, last_name, id, password) values (1 , 'michalina.kowalska@gmail.com', 'Michalina', 'Kowalska',  1, 'password');


insert into restaurant (address_id, cuisine, name, owner_id, phone_number, id) values (1, 'ITALIAN', 'Pastini', 1, '71 12345', 1);
insert into restaurant (address_id, cuisine, name, owner_id, phone_number, id) values (2, 'FUSION', 'Seven', 1, '22 12345', 2);
insert into restaurant (address_id, cuisine, name, owner_id, phone_number, id) values (3, 'THAI', 'Woo Thai', 2, '22 12345', 3);

insert into restaurant_table (restaurant_id, seats, id) values (1 , 2, 1);
insert into restaurant_table (restaurant_id, seats, id) values (1 , 2, 2);

insert into restaurant_table (restaurant_id, seats, id) values (2 , 2, 3);
insert into restaurant_table (restaurant_id, seats, id) values (2 , 2, 4);

insert into restaurant_table (restaurant_id, seats, id) values (3 , 4, 5);
insert into restaurant_table (restaurant_id, seats, id) values (3 , 4, 6);