package com.vnborx.dao;

import com.vnborx.pojo.Department;
import com.vnborx.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {
    // Simulate data in the database
    private static Map<Integer, Employee> employees = null;
    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<>();
        employees.put(1001, new Employee(1001, "AA", "AA@gmail.com", 0, new Department(101, "Research department")));
        employees.put(1002, new Employee(1002, "BB", "BB@gmail.com", 1, new Department(102, "Marketing department")));
        employees.put(1003, new Employee(1003, "CC", "CC@gmail.com", 0, new Department(103, "Development department")));
        employees.put(1004, new Employee(1004, "DD", "DD@gmail.com", 1, new Department(104, "Management department")));
        employees.put(1005, new Employee(1005, "EE", "EE@gmail.com", 0, new Department(105, "Safety department")));
    }

    private Integer initId = 1006;
    public void add(Employee employee) {
        if (employee.getId() == null)
            employee.setId(initId++);
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    public Collection<Employee> getAll() {
        return employees.values();
    }

    public Employee getEmployeeById(Integer id) {
        return employees.get(id);
    }

    public void delete(Integer id) {
        employees.remove(id);
    }
}
