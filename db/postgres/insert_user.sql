insert into f_user (uuid, email, first_name, last_name, roles, pw, account_expired, locked, enabled)
VALUES (uuid_in(md5(random()::text || now()::text)::cstring),
        'rubenski@gmail.com', 'Ruben', 'van Loen', '{ "grants" : ["ADMIN_USER", "STANDARD_USER"]}',
        'hello',
        false, false, true);