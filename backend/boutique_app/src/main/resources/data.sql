insert ignore into bed_type (id, name, size) values (1, 'Single', 1);
insert ignore into bed_type (id, name, size) values (2, 'Double', 2);
insert ignore into bed_type (id, name, size) values (3, 'Queen', 2);
insert ignore into bed_type (id, name, size) values (4, 'King', 2);

insert ignore into room_type (id, name, description) values (1, 'Standard', 'Standard room');
insert ignore into room_type (id, name, description) values (2, 'Deluxe', 'Deluxe room');
insert ignore into room_type (id, name, description) values (3, 'Suite', 'Suite room');

insert ignore into room_extra (id, name) values (1, 'Balcony');
insert ignore into room_extra (id, name) values (2, 'Sea view');
insert ignore into room_extra (id, name) values (3, 'Mountain view');
insert ignore into room_extra (id, name) values (4, 'City view');
insert ignore into room_extra (id, name) values (5, 'Garden view');
insert ignore into room_extra (id, name) values (6, 'Pool view');
insert ignore into room_extra (id, name) values (7, 'Terrace');
insert ignore into room_extra (id, name) values (8, 'Patio');
insert ignore into room_extra (id, name) values (9, 'Kitchen');
insert ignore into room_extra (id, name) values (10, 'Living room');
insert ignore into room_extra (id, name) values (11, 'Dining room');
insert ignore into room_extra (id, name) values (12, 'Fireplace');
insert ignore into room_extra (id, name) values (13, 'Hot tub');
insert ignore into room_extra (id, name) values (14, 'Air conditioning');
insert ignore into room_extra (id, name) values (15, 'WIFI');
insert ignore into room_extra (id, name) values (16, 'Extra large toilet');


insert ignore into room (id, name, price_per_night, image_url, type_id) VALUES (1, 'Room 1', 100.0, 'http://localhost:8080/rooms/1/image', 1);
insert ignore into room (id, name, price_per_night, image_url, type_id) VALUES (2, 'Room 2', 110.0, 'http://localhost:8080/rooms/2/image', 1);
insert ignore into room (id, name, price_per_night, image_url, type_id) VALUES (3, 'Room 3', 120.0, 'http://localhost:8080/rooms/3/image', 1);
insert ignore into room (id, name, price_per_night, image_url, type_id) VALUES (4, 'Room 4', 130.0, 'http://localhost:8080/rooms/4/image', 1);
insert ignore into room (id, name, price_per_night, image_url, type_id) VALUES (5, 'Room 5', 140.0, 'http://localhost:8080/rooms/5/image', 2);
insert ignore into room (id, name, price_per_night, image_url, type_id) VALUES (6, 'Room 6', 150.0, 'http://localhost:8080/rooms/6/image', 2);
insert ignore into room (id, name, price_per_night, image_url, type_id) VALUES (7, 'Room 7', 160.0, 'http://localhost:8080/rooms/7/image', 3);
insert ignore into room (id, name, price_per_night, image_url, type_id) VALUES (8, 'Room 8', 170.0, 'http://localhost:8080/rooms/8/image', 4);

insert ignore into beds_in_rooms (amount, room_id, bed_type_id) VALUE (1, 1, 1);
insert ignore into beds_in_rooms (amount, room_id, bed_type_id) VALUE (1, 2, 1);
insert ignore into beds_in_rooms (amount, room_id, bed_type_id) VALUE (1, 3, 1);
insert ignore into beds_in_rooms (amount, room_id, bed_type_id) VALUE (2, 4, 1);
insert ignore into beds_in_rooms (amount, room_id, bed_type_id) VALUE (1, 5, 2);
insert ignore into beds_in_rooms (amount, room_id, bed_type_id) VALUE (1, 6, 2);
insert ignore into beds_in_rooms (amount, room_id, bed_type_id) VALUE (2, 7, 2);
insert ignore into beds_in_rooms (amount, room_id, bed_type_id) VALUE (1, 8, 3);
insert ignore into beds_in_rooms (amount, room_id, bed_type_id) VALUE (1, 8, 4);

insert ignore into room_type_standard_extras (room_type_id, standard_extras_id) VALUE (1, 14);
insert ignore into room_type_standard_extras (room_type_id, standard_extras_id) VALUE (1, 15);
insert ignore into room_type_standard_extras (room_type_id, standard_extras_id) VALUE (1, 9);
insert ignore into room_type_standard_extras (room_type_id, standard_extras_id) VALUE (2, 14);
insert ignore into room_type_standard_extras (room_type_id, standard_extras_id) VALUE (2, 1);
insert ignore into room_type_standard_extras (room_type_id, standard_extras_id) VALUE (2, 11);
insert ignore into room_type_standard_extras (room_type_id, standard_extras_id) VALUE (3, 1);
insert ignore into room_type_standard_extras (room_type_id, standard_extras_id) VALUE (3, 11);
insert ignore into room_type_standard_extras (room_type_id, standard_extras_id) VALUE (3, 12);
insert ignore into room_type_standard_extras (room_type_id, standard_extras_id) VALUE (3, 14);
insert ignore into room_type_standard_extras (room_type_id, standard_extras_id) VALUE (3, 15);
insert ignore into room_type_standard_extras (room_type_id, standard_extras_id) VALUE (3, 13);
insert ignore into room_type_standard_extras (room_type_id, standard_extras_id) VALUE (3, 1);

insert ignore into room_extras (room_id, extras_id) VALUE (1, 1);
insert ignore into room_extras (room_id, extras_id) VALUE (1, 2);
insert ignore into room_extras (room_id, extras_id) VALUE (2, 3);
insert ignore into room_extras (room_id, extras_id) VALUE (3, 4);
insert ignore into room_extras (room_id, extras_id) VALUE (4, 5);
insert ignore into room_extras (room_id, extras_id) VALUE (5, 2);
insert ignore into room_extras (room_id, extras_id) VALUE (6, 7);
insert ignore into room_extras (room_id, extras_id) VALUE (7, 3);
insert ignore into room_extras (room_id, extras_id) VALUE (8, 2);