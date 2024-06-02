insert ignore into bed_type (id, name, size) values (1, 'Single', 1);
insert ignore into bed_type (id, name, size) values (2, 'Double', 2);
insert ignore into bed_type (id, name, size) values (3, 'Queen', 2);
insert ignore into bed_type (id, name, size) values (4, 'King', 2);

insert ignore into room_type (id, name, description) values (1, 'Standard', 'Standard room');
insert ignore into room_type (id, name, description) values (2, 'Deluxe', 'Deluxe room');
insert ignore into room_type (id, name, description) values (3, 'Suite', 'Suite room');

insert ignore into room_feature (id, name) values (1, 'Balcony');
insert ignore into room_feature (id, name) values (2, 'Sea view');
insert ignore into room_feature (id, name) values (3, 'Mountain view');
insert ignore into room_feature (id, name) values (4, 'City view');
insert ignore into room_feature (id, name) values (5, 'Garden view');
insert ignore into room_feature (id, name) values (6, 'Pool view');
insert ignore into room_feature (id, name) values (7, 'Terrace');
insert ignore into room_feature (id, name) values (8, 'Patio');
insert ignore into room_feature (id, name) values (9, 'Kitchen');
insert ignore into room_feature (id, name) values (10, 'Living room');
insert ignore into room_feature (id, name) values (11, 'Dining room');
insert ignore into room_feature (id, name) values (12, 'Fireplace');
insert ignore into room_feature (id, name) values (13, 'Hot tub');
insert ignore into room_feature (id, name) values (14, 'Air conditioning');


insert ignore into room (id, image_url, type_id) VALUES (1, null, 1);
insert ignore into room (id, image_url, type_id) VALUES (2, null, 1);
insert ignore into room (id, image_url, type_id) VALUES (3, null, 1);
insert ignore into room (id, image_url, type_id) VALUES (4, null, 1);
insert ignore into room (id, image_url, type_id) VALUES (5, null, 2);
insert ignore into room (id, image_url, type_id) VALUES (6, null, 2);
insert ignore into room (id, image_url, type_id) VALUES (7, null, 2);
insert ignore into room (id, image_url, type_id) VALUES (8, null, 3);

insert ignore into beds_in_rooms (amount, room_id, bed_type_id) VALUE (1, 1, 1);
insert ignore into beds_in_rooms (amount, room_id, bed_type_id) VALUE (1, 2, 1);
insert ignore into beds_in_rooms (amount, room_id, bed_type_id) VALUE (1, 3, 1);
insert ignore into beds_in_rooms (amount, room_id, bed_type_id) VALUE (2, 4, 1);
insert ignore into beds_in_rooms (amount, room_id, bed_type_id) VALUE (1, 5, 2);
insert ignore into beds_in_rooms (amount, room_id, bed_type_id) VALUE (1, 6, 2);
insert ignore into beds_in_rooms (amount, room_id, bed_type_id) VALUE (2, 7, 2);
insert ignore into beds_in_rooms (amount, room_id, bed_type_id) VALUE (1, 8, 3);
insert ignore into beds_in_rooms (amount, room_id, bed_type_id) VALUE (1, 8, 4);

insert ignore into room_type_standard_features (room_type_id, standard_features_id) VALUE (1, 14);
insert ignore into room_type_standard_features (room_type_id, standard_features_id) VALUE (1, 9);
insert ignore into room_type_standard_features (room_type_id, standard_features_id) VALUE (2, 14);
insert ignore into room_type_standard_features (room_type_id, standard_features_id) VALUE (2, 1);
insert ignore into room_type_standard_features (room_type_id, standard_features_id) VALUE (2, 11);
insert ignore into room_type_standard_features (room_type_id, standard_features_id) VALUE (3, 1);
insert ignore into room_type_standard_features (room_type_id, standard_features_id) VALUE (3, 11);
insert ignore into room_type_standard_features (room_type_id, standard_features_id) VALUE (3, 12);
insert ignore into room_type_standard_features (room_type_id, standard_features_id) VALUE (3, 14);
insert ignore into room_type_standard_features (room_type_id, standard_features_id) VALUE (3, 13);
insert ignore into room_type_standard_features (room_type_id, standard_features_id) VALUE (3, 1);

insert ignore into room_features (room_id, features_id) VALUE (1, 1);
insert ignore into room_features (room_id, features_id) VALUE (1, 2);
insert ignore into room_features (room_id, features_id) VALUE (2, 3);
insert ignore into room_features (room_id, features_id) VALUE (3, 4);
insert ignore into room_features (room_id, features_id) VALUE (4, 5);
insert ignore into room_features (room_id, features_id) VALUE (5, 2);
insert ignore into room_features (room_id, features_id) VALUE (6, 7);
insert ignore into room_features (room_id, features_id) VALUE (7, 3);
insert ignore into room_features (room_id, features_id) VALUE (8, 2);