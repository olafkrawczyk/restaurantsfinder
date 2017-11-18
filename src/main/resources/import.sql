insert into address (city, postal_code, street, id) values ('Wroclaw', '50-363', 'Rynek 6', 1);
insert into address (city, postal_code, street, id) values ('Warszawa', '00-001', 'Rynek 3', 2);
insert into address (city, postal_code, street, id) values ('Katowice', '40-201', 'Rynek 2', 3);

insert into owner (address_id, email_address, first_name, last_name, id, password) values (1 , 'john.doe@gmail.com', 'John', 'Doe', 1, 'password');
insert into owner (address_id, email_address, first_name, last_name, id, password) values (2 , 'jan.kowalski@gmail.com', 'Jan', 'Kowalski', 2, 'password');

insert into client (address_id, email_address, first_name, last_name, id, password) values (1 , 'michalina.kowalska@gmail.com', 'Michalina', 'Kowalska',  1, 'password');


insert into restaurant (address_id, cuisine, name, owner_id, phone_number, id, description, open_hour, close_hour, ) values (1, 'ITALIAN', 'Pastini', 1, '71 12345', 1, 'Fun italian restaurant', '07:00', '20:00');
insert into restaurant (address_id, cuisine, name, owner_id, phone_number, id, open_hour, close_hour) values (2, 'FUSION', 'Seven', 1, '22 12345', 2, '07:00', '20:00');
insert into restaurant (address_id, cuisine, name, owner_id, phone_number, id, open_hour, close_hour) values (3, 'THAI', 'Woo Thai', 2, '22 12345', 3, '07:00', '20:00');

insert into restaurant_table (restaurant_id, seats, id, restaurant_table_id) values (1 , 2, 1, 'A1');
insert into restaurant_table (restaurant_id, seats, id, restaurant_table_id) values (1 , 2, 2, 'A2');

insert into restaurant_table (restaurant_id, seats, id, restaurant_table_id) values (2 , 2, 3, 'B1');
insert into restaurant_table (restaurant_id, seats, id, restaurant_table_id) values (2 , 2, 4, 'B2');

insert into restaurant_table (restaurant_id, seats, id, restaurant_table_id) values (3 , 4, 5, 'C1');
insert into restaurant_table (restaurant_id, seats, id, restaurant_table_id) values (3 , 4, 6, 'C2');

insert into menu(id) values (1);
insert into menu_item(id, description, dish_name, price, menu_id) values (1,'Smoked salmon with bitroot', 'Smoked salmon', 22.0, 1);
insert into menu_item(id, description, dish_name, price, menu_id) values (2,'Beef burger, french fries', 'Classic burger', 12.0, 1);
insert into menu_item(id, description, dish_name, price, menu_id) values (3,'Spagetti carbonara served with garlic olive oil', 'Carbonara', 20.50, 1);
insert into menu_item(id, description, dish_name, price, menu_id) values (4,'Pulled pork with sweet-sour paprika', 'Pulled pork', 18.0, 1);

update restaurant set menu_id = 1 where id = 1;

insert into reservation(ID,	CREATION_DATE,	RESERVATION_DATE, RESERVATION_HOURS,	RESERVATION_STATUS,	CLIENT_ID,	RETAURANT_ID, TABLE_ID) values (1, '2017-11-01', '2017-11-06 12:00:00', 1, 'PENDING', 1, 1, 1);