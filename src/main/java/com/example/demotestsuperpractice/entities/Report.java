package com.example.demotestsuperpractice.entities;

import java.util.List;

public class Report {
    private Employee employee;
    private Job job;
    private Department department;
    private List<Responsibilities> responsibilities;

    public Report(Employee employee, Job job, Department department, List<Responsibilities> responsibilities) {
        this.employee = employee;
        this.job = job;
        this.department = department;
        this.responsibilities = responsibilities;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Responsibilities> getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(List<Responsibilities> responsibilities) {
        this.responsibilities = responsibilities;
    }
}
