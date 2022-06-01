INSERT INTO public.user_role (name) VALUES ('ROLE_USER');
INSERT INTO public.user_role (name) VALUES ('ROLE_ADMIN');
INSERT INTO public.user_role (name) VALUES ('ROLE_CLIENT');
INSERT INTO public.user_role (name) VALUES ('ROLE_BOAT_OWNER');
INSERT INTO public.user_role (name) VALUES ('ROLE_COTTAGE_OWNER');
INSERT INTO public.user_role (name) VALUES ('ROLE_INSTRUCTOR');

INSERT INTO public.client (id, address, app_user_type, city, country, email, enabled, last_password_reset_date, name, password, surname, telephone) VALUES (nextval('user_seq_gen'), 'Resavska 5', 0, 'Novi Sad', 'Serbia', 'nikola.iv.99@gmail.com', true, null, 'Nikola', '$2a$10$ZOy/T/.nqSeJZ7rUU9b2Z.hREwrLXVfKRUhjcBJWyTUmMkIVC0YYS', 'Ivanovic', '+381691712999');
INSERT INTO public.app_user_user_role(user_id, role_id) VALUES (1, 1);
INSERT INTO public.app_user_user_role(user_id, role_id) VALUES (1, 3);

INSERT INTO public.cottage_owner(id, address, app_user_type, city, country, email, enabled, last_password_reset_date, name, password, surname, telephone) VALUES (nextval('user_seq_gen'), 'Iva Andrica 23', 2, 'Novi Sad', 'Serbia', 'bogdanaz207@gmail.com', true, null, 'Bogdana', '$2a$10$ZOy/T/.nqSeJZ7rUU9b2Z.hREwrLXVfKRUhjcBJWyTUmMkIVC0YYS', 'Zivkovic', '+381603212015');
INSERT INTO public.app_user_user_role(user_id, role_id) VALUES (2, 1);
INSERT INTO public.app_user_user_role(user_id, role_id) VALUES (2, 5);

INSERT INTO public.cottage(id, behavior_rules, description, last_update_date, max_number_of_people, name, price_per_day, service_type, version, cottage_owner_id) VALUES (nextval('service_seq_gen'), 'Nema pusenja', 'Prelepa koliba', null, 5, 'Koliba Aladin i sinovi', 30, 1, 0, 2);
INSERT INTO public.location (city, latitude, longitude, "number", street, zip_code, service_id) VALUES ('Belgrade', '44.78932489153337', '20.448370203245677', '4', 'Качаничка', '11000', 1);

INSERT INTO public.time_range(end_date, start_date, service_id) VALUES ('2022-06-15 00:00:00', '2022-06-09 00:00:00', 1);

INSERT INTO public.reservation (duration_in_days, number_of_people, price, reported, reservation_start_date_and_time, client_id, service_id) VALUES (3, 5, 90, false, '2022-06-06 00:00:00', 1, 1);