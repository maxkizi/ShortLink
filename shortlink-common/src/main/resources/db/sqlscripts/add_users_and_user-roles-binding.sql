INSERT INTO application_user (first_name, last_name, login, password, is_account_non_expired, is_account_non_locked,
                              is_credentials_non_expired, is_enabled)

VALUES ('Максим', 'Кизилов', 'admin', '$2a$10$CltVBn0P/ApkBXDCKrKJdOgnECpjEsiPusBGLhJakhSYe3E9v5uzu', true,
        true, true, true),
       ('Иван', 'Иванов', 'guest', '$2a$10$pHRlpjxn3tS3jN17LB9vquK1XxRqItqAnEgFbPElHEUy3cQkJtmDe',
        true, true, true, true);

INSERT INTO application_user_role_binding(role_id, application_user_id)
VALUES ('ROLE_ADMIN', 1),
       ('ROLE_GUEST', 1),
       ('ROLE_GUEST', 2);