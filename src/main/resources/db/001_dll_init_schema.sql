CREATE TABLE IF NOT EXISTS employees(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(50),
    surname VARCHAR(50),
    idt varchar(50),
    hire_date TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE IF NOT EXISTS persons (
    id SERIAL PRIMARY KEY NOT NULL ,
    login VARCHAR(2000),
    password VARCHAR(2000),
    employee_id INT REFERENCES employees(id)
);