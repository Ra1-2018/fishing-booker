INSERT INTO public.user_role (name) VALUES ('ROLE_USER');
INSERT INTO public.user_role (name) VALUES ('ROLE_ADMIN');
INSERT INTO public.user_role (name) VALUES ('ROLE_CLIENT');
INSERT INTO public.user_role (name) VALUES ('ROLE_BOAT_OWNER');
INSERT INTO public.user_role (name) VALUES ('ROLE_COTTAGE_OWNER');
INSERT INTO public.user_role (name) VALUES ('ROLE_INSTRUCTOR');

INSERT INTO public.client (id, address, app_user_type, city, country, email, enabled, last_password_reset_date, name, password, surname, telephone, loyalty_type, points) VALUES (nextval('user_seq_gen'), 'Resavska 5', 0, 'Novi Sad', 'Serbia', 'nikola.iv.99@gmail.com', true, null, 'Nikola', '$2a$10$ZOy/T/.nqSeJZ7rUU9b2Z.hREwrLXVfKRUhjcBJWyTUmMkIVC0YYS', 'Ivanovic', '+381691712999', 0, 10);
INSERT INTO public.app_user_user_role(user_id, role_id) VALUES (1, 1);
INSERT INTO public.app_user_user_role(user_id, role_id) VALUES (1, 3);

INSERT INTO public.cottage_owner(id, address, app_user_type, city, country, email, enabled, last_password_reset_date, name, password, surname, telephone, loyalty_type, points) VALUES (nextval('user_seq_gen'), 'Iva Andrica 23', 2, 'Novi Sad', 'Serbia', 'bogdanaz207@gmail.com', true, null, 'Bogdana', '$2a$10$ZOy/T/.nqSeJZ7rUU9b2Z.hREwrLXVfKRUhjcBJWyTUmMkIVC0YYS', 'Zivkovic', '+381603212015', 0, 0);
INSERT INTO public.app_user_user_role(user_id, role_id) VALUES (2, 1);
INSERT INTO public.app_user_user_role(user_id, role_id) VALUES (2, 5);

INSERT INTO public.boat_owner(id, address, app_user_type, city, country, email, enabled, last_password_reset_date, name, password, surname, telephone, loyalty_type, points) VALUES (nextval('user_seq_gen'), 'Puskinova 4', 1, 'Novi Sad', 'Serbia', 'mila@gmail.com', true, null, 'Mila', '$2a$10$ZOy/T/.nqSeJZ7rUU9b2Z.hREwrLXVfKRUhjcBJWyTUmMkIVC0YYS', 'Milic', '+381603211415', 0, 0);
INSERT INTO public.app_user_user_role(user_id, role_id) VALUES (3, 1);
INSERT INTO public.app_user_user_role(user_id, role_id) VALUES (3, 4);

INSERT INTO public.instructor(id, address, app_user_type, city, country, email, enabled, last_password_reset_date, name, password, surname, telephone, loyalty_type, points) VALUES (nextval('user_seq_gen'), 'Safarikova 30', 3, 'Zrenjanin', 'Serbia', 'porodica1aleksic@gmail.com', true, null, 'Danijela', '$2a$10$ZOy/T/.nqSeJZ7rUU9b2Z.hREwrLXVfKRUhjcBJWyTUmMkIVC0YYS', 'Aleksic', '+381648116715', 0, 0);
INSERT INTO public.app_user_user_role(user_id, role_id) VALUES (4, 1);
INSERT INTO public.app_user_user_role(user_id, role_id) VALUES (4, 6);

INSERT INTO public.administrator(id, address, app_user_type, city, country, email, enabled, last_password_reset_date, name, password, surname, telephone, admin, first_reg, loyalty_type, points) VALUES (nextval('user_seq_gen'), 'Safarikova 30', 4, 'Zrenjanin', 'Serbia', 'lenkaisidora.aleksic@gmail.com', true, null, 'Lenka Isidora', '$2a$10$ZOy/T/.nqSeJZ7rUU9b2Z.hREwrLXVfKRUhjcBJWyTUmMkIVC0YYS', 'Aleksic', '+381628296361', true, false, 0, 0);
INSERT INTO public.app_user_user_role(user_id, role_id) VALUES (5, 1);
INSERT INTO public.app_user_user_role(user_id, role_id) VALUES (5, 2);

INSERT INTO public.cottage(id, behavior_rules, description, last_update_date, max_number_of_people, name, price_per_day, service_type, version, cottage_owner_id) VALUES (nextval('service_seq_gen'), 'Nema pusenja', 'Prelepa koliba', null, 5, 'Koliba Aladin i sinovi', 30, 1, 0, 2);
INSERT INTO public.location (city, latitude, longitude, "number", street, zip_code, service_id) VALUES ('Belgrade', '44.78932489153337', '20.448370203245677', '4', 'Качаничка', '11000', 1);

INSERT INTO public.boat(id, behavior_rules, description, last_update_date, max_number_of_people, name, price_per_day, service_type, version, cancellation_terms, engine_power, fishing_equipment, length, maximum_velocity, navigation_equipment, number_of_engines, type, boat_owner_id) VALUES (nextval('service_seq_gen'), 'No pets allowed', 'Veliki brod', null, 6, 'Nojeva barka', 20, 0, 0, 'Otkazivanje 2 dana unapred', '8-10 MW', '3 pecaljke', '20m', '100km/h', 'GPS', 2, 'Trawler', 3);
INSERT INTO public.location (city, latitude, longitude, "number", street, zip_code, service_id) VALUES ('Belgrade', '44.82828946849244', '20.474567942652342', '2-4', 'Dunavski kej', '11000', 2);

INSERT INTO public.adventure(id, behavior_rules, description, last_update_date, max_number_of_people, name, price_per_day, service_type, version, cancellation, fishing_gear, instructor_biography, instructor_id) VALUES (nextval('service_seq_gen'), 'Nema psovanja', 'Najzanimljivija pecaroska avantura', null, 20, 'Pecajte s nama', 40, 2, 0, 0, 'Varalice', 'Mnogo dobar instruktor', 4);
INSERT INTO public.location (city, latitude, longitude, "number", street, zip_code, service_id) VALUES ('Belgrade', '44.78932489153337', '20.448370203245677', '4', 'Качаничка', '11000', 3);

INSERT INTO public.time_range(available, end_date, start_date, service_id) VALUES (true, '2022-06-15 00:00:00', '2022-06-09 00:00:00', 1);
INSERT INTO public.time_range(available, end_date, start_date, service_id) VALUES (true, '2022-06-30 00:00:00', '2022-06-10 00:00:00', 2);
INSERT INTO public.time_range(available, end_date, start_date, service_id) VALUES (false, '2022-06-15 00:00:00', '2022-06-09 00:00:00', 3);

INSERT INTO public.reservation (duration_in_days, number_of_people, price, reported, reservation_start_date_and_time, client_id, service_id) VALUES (3, 5, 90, false, '2022-07-20 00:00:00', 1, 1);

INSERT INTO public.subscription(client_id, service_id) VALUES (1, 1);
INSERT INTO public.subscription(client_id, service_id) VALUES (1, 2);
INSERT INTO public.subscription(client_id, service_id) VALUES (1, 3);

INSERT INTO public.action(duration_in_days, max_number_of_people, price, start_time, service_id) VALUES (3, 3, 70, '2022-06-20 00:00:00', 1);
INSERT INTO public.action(duration_in_days, max_number_of_people, price, start_time, service_id) VALUES (4, 6, 100, '2022-06-06 00:00:00', 2);
INSERT INTO public.action(duration_in_days, max_number_of_people, price, start_time, service_id) VALUES (3, 3, 70, '2022-06-20 00:00:00', 3);

INSERT INTO public.loyalty_program(percent_for_bronze, percent_for_gold, percent_for_silver, points_for_bronze, points_for_gold, points_for_silver) VALUES (10, 30, 20, 100, 300, 200);
