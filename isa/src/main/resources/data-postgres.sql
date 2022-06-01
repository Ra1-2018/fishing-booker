INSERT INTO USER_ROLE (name) VALUES ('ROLE_USER');
INSERT INTO USER_ROLE (name) VALUES ('ROLE_ADMIN');
INSERT INTO USER_ROLE (name) VALUES ('ROLE_CLIENT');
INSERT INTO USER_ROLE (name) VALUES ('ROLE_BOAT_OWNER');
INSERT INTO USER_ROLE (name) VALUES ('ROLE_COTTAGE_OWNER');
INSERT INTO USER_ROLE (name) VALUES ('ROLE_INSTRUCTOR');

INSERT INTO CLIENT (address, app_user_type, city, country, email, enabled, last_password_reset_date, name, password, surname, telephone) VALUES ('Resavska 5', 0, 'Novi Sad', 'Serbia', 'nikola.iv.99@gmail.com', true, null, 'Nikola', '$2a$10$h8WSkNGWrrVxRudBl1vGkuJ89Sw/tyk9cXhekb5HU5tMTwJivRsFS', 'Ivanovic', '+381691712999');
INSERT INTO APP_USER_USER_ROLE (user_id, role_id) VALUES (1, 1);
INSERT INTO APP_USER_USER_ROLE (user_id, role_id) VALUES (1, 3);

INSERT INTO COTTAGE (behavior_rules, description, last_update_date, max_number_of_people, name, price_per_day, service_type, version, rooms_total_number, cottage_owner_id) VALUES ('Nema pusenja unutra', 'Prelepa koliba', '2022-05-30 20:46:18.266', 5, 'Koliba Aladin i sinovi', 30, 1, 1, 3, null);

INSERT INTO RESERVATION (duration_in_days, number_of_people, price, reported, reservation_start_date_and_time, client_id, service_id) VALUES (3, 5, 90, false, '2022-05-05 00:00:00', 1, 1);