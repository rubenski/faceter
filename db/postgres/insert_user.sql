insert into f_user (uuid, email, first_name, last_name, roles)
VALUES (uuid_in(md5(random()::text || now()::text)::cstring),
        'rubenski@gmail.com', 'Ruben', 'van Loen', '["ADMIN_USER", "STANDARD_USER"]');