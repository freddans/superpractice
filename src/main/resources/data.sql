-- join together
CREATE TABLE employee_job (
    employee_id INT,
    job_id INT,
    PRIMARY KEY (employee_id, job_id),
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id),
    FOREIGN KEY (job_id) REFERENCES jobs(job_id)
);

CREATE TABLE employee_department (
    employee_id INT,
    department_id INT,
    PRIMARY KEY (employee_id, department_id),
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id),
    FOREIGN KEY (department_id) REFERENCES departments(department_id)
);

INSERT INTO employees (nickname) VALUES ('Hannes'), ('Kalle');
INSERT INTO jobs (jobname) VALUES ('janitor'), ('teacher');
INSERT INTO departments (department) VALUES ('toilet'), ('classroom');

INSERT INTO employee_job (employee_id, job_id) VALUES (1, 1), (2, 2);
INSERT INTO employee_department (employee_id, department_id) VALUES (1,1), (2,2);

SELECT employees.nickname, jobs.jobname
FROM employees
    JOIN employee_job
        ON employees.employee_id = employee_job.employee_id
    JOIN jobs
        ON employee_job.job_id = jobs.job_id;

INSERT INTO responsibilities (responsibility) VALUES ('Clean'), ('Throw garbage'), ('Homework'), ('Teach');


CREATE TABLE employee_responsibility (
    employee_id INT,
    responsibility_id INT,
    PRIMARY KEY (employee_id, responsibility_id),
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id),
    FOREIGN KEY (responsibility_id) REFERENCES responsibilities(responsibility_id)
);

INSERT INTO employee_responsibility (employee_id, responsibility_id) VALUES (1, 1), (1, 2), (2, 3), (2, 4);