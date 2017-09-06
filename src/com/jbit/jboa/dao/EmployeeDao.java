package com.jbit.jboa.dao;

import java.util.List;

import com.jbit.jboa.entity.Department;
import com.jbit.jboa.entity.Employee;

public interface EmployeeDao {
    public List<Employee> findEmployee(Employee emp);

    public Employee get(String sn);

    public Department get(Integer sn);

    public List<Employee> findManager(String sn);
}
