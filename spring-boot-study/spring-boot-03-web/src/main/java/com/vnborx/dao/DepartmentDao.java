package com.vnborx.dao;

import com.vnborx.pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DepartmentDao {
    // Simulate data in the database
    private static Map<Integer, Department> departments = null;

    static {
        departments = new HashMap<>();
        departments.put(101, new Department(101, "Research department"));
        departments.put(102, new Department(102, "Marketing department"));
        departments.put(103, new Department(103, "Development department"));
        departments.put(104, new Department(104, "Management department"));
        departments.put(105, new Department(105, "Safety department"));
    }

    public Collection<Department> getDepartments() {
        return departments.values();
    }

    public Department getDepartmentById(Integer id) {
        return departments.get(id);
    }
}
