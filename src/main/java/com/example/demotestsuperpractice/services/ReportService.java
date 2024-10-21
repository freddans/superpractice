package com.example.demotestsuperpractice.services;

import com.example.demotestsuperpractice.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {
    private final EntityManager entityManager;

    @Autowired
    public ReportService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Report> getAllEmployeeReports() {
        String sql = "SELECT e.employee_id, e.nickname, j.job_id, j.jobname, d.department_id, d.department " +
                "FROM employees e " +
                "JOIN employee_job ej ON e.employee_id = ej.employee_id " +
                "JOIN jobs j ON j.job_id = ej.job_id " +
                "JOIN employee_department ed ON e.employee_id = ed.employee_id " +
                "JOIN departments d ON d.department_id = ed.department_id";

        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> results = query.getResultList();

        List<Report> reportsList = new ArrayList<>();
        for (Object[] result : results) {
            Integer employee_id = (Integer) result[0];
            String nickName = (String) result[1];
            Integer job_id = (Integer) result[2];
            String jobName = (String) result[3];
            Integer department_id = (Integer) result[4];
            String departmentName = (String) result[5];

            // Create objects for Employee, Job, and Department
            Employee employee = new Employee();
            employee.setEmployee_id(employee_id);
            employee.setNickName(nickName);

            Job job = new Job();
            job.setJob_id(job_id);
            job.setJobName(jobName);

            Department department = new Department();
            department.setDepartment_id(department_id);
            department.setDepartmentName(departmentName);

            // Fetch responsibilities for this employee
            List<Responsibilities> responsibilities = getResponsibilitiesByEmployeeId(employee_id);

            // Create a report object with these entities
            reportsList.add(new Report(employee, job, department, responsibilities));
        }

        // Print reports for verification
        for (Report report : reportsList) {
            System.out.println("Employee: " + report.getEmployee().getNickName());
            System.out.println("Job: " + report.getJob().getJobName());
            System.out.println("Department: " + report.getDepartment().getDepartmentName());
            System.out.println("Responsibilities: " + report.getResponsibilities());
        }

        return reportsList;
    }

    public List<Report> getReportsByEmployeeIds(List<Integer> ids) {
        List<Report> reportsList = new ArrayList<>();

        for (Integer employeeId : ids) {
            String sql = "SELECT e.employee_id, e.nickname, j.job_id, j.jobname, d.department_id, d.department " +
                    "FROM employees e " +
                    "JOIN employee_job ej ON e.employee_id = ej.employee_id " +
                    "JOIN jobs j ON j.job_id = ej.job_id " +
                    "JOIN employee_department ed ON e.employee_id = ed.employee_id " +
                    "JOIN departments d ON d.department_id = ed.department_id " +
                    "WHERE e.employee_id = :employeeId";

            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("employeeId", employeeId);
            List<Object[]> results = query.getResultList();

            for (Object[] result : results) {
                Integer employee_id = (Integer) result[0];
                String nickName = (String) result[1];
                Integer job_id = (Integer) result[2];
                String jobName = (String) result[3];
                Integer department_id = (Integer) result[4];
                String departmentName = (String) result[5];

                Employee employee = new Employee();
                employee.setEmployee_id(employee_id);
                employee.setNickName(nickName);

                Job job = new Job();
                job.setJob_id(job_id);
                job.setJobName(jobName);

                Department department = new Department();
                department.setDepartment_id(department_id);
                department.setDepartmentName(departmentName);

                List<Responsibilities> responsibilities = getResponsibilitiesByEmployeeId(employee_id);

                reportsList.add(new Report(employee, job, department, responsibilities));
            }
        }

        return reportsList;
    }

    private List<Responsibilities> getResponsibilitiesByEmployeeId(Integer employeeId) {
        String sql = "SELECT r.responsibility_id, r.responsibility " +
                "FROM responsibilities r " +
                "JOIN employee_responsibility er ON r.responsibility_id = er.responsibility_id " +
                "WHERE er.employee_id = :employeeId";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("employeeId", employeeId);

        List<Object[]> results = query.getResultList();
        List<Responsibilities> responsibilitiesList = new ArrayList<>();

        for (Object[] result : results) {
            Integer responsibility_id = (Integer) result[0];
            String responsibility = (String) result[1];

            Responsibilities res = new Responsibilities();
            res.setResponsibility_id(responsibility_id);
            res.setResponsibility(responsibility);

            responsibilitiesList.add(res);
        }

        return responsibilitiesList;
    }
}
