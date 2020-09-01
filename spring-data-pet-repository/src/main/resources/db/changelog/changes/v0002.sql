--liquibase formatted sql
--changeset andrew:remove-email-column-from-users-table
ALTER TABLE users DROP COLUMN email;