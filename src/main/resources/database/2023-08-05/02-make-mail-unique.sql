--liquibase formatted sql
--changeset zaitis:2
ALTER TABLE employee
ADD CONSTRAINT unique_mail
UNIQUE (mail);

