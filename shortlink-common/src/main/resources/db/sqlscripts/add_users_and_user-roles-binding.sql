INSERT INTO application_user (username, password, last_name, first_name, is_account_non_expired, is_account_non_locked,
                              is_credentials_non_expired, is_enabled)

VALUES ('admin_user', '$2a$10$CltVBn0P/ApkBXDCKrKJdOgnECpjEsiPusBGLhJakhSYe3E9v5uzu', 'Кизилов', 'Максим', true,
        true, true, true),
       ('guest_user', '$2a$10$pHRlpjxn3tS3jN17LB9vquK1XxRqItqAnEgFbPElHEUy3cQkJtmDe', 'Иван', 'Иванов', true,
        true, true, true);

INSERT INTO application_user_role_binding(role_id, username)
VALUES ('ROLE_ADMIN', 'admin_user'),
       ('ROLE_GUEST', 'admin_user'),
       ('ROLE_GUEST', 'guest_user');