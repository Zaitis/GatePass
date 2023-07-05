--liquibase formatted sql
--changeset zaitis:1
CREATE TABLE employee (
       id bigserial not null,
        first_name varchar(255),
        last_name varchar(255),
        mail varchar(255),
        password varchar(255),
        phone varchar(255),
        role varchar(255),
        company_id bigint,
        primary key (id)
    );

CREATE TABLE car (
       id bigserial not null,
        brand varchar(255),
        model varchar(255),
        plate varchar(255),
        employee_id bigint,
        primary key (id)
    );

    CREATE TABLE company (
       id bigserial not null,
        company_name varchar(255),
        mail varchar(255),
        phone varchar(255),
        primary key (id)
    );

    CREATE TABLE gate_pass (
       id bigserial not null,
       created_date timestamp(6),
        accepted_date timestamp(6),
        is_accepted boolean,
        car_id bigint,
        primary key (id)
    );