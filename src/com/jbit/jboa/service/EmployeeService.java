package com.jbit.jboa.service;

import com.jbit.jboa.entity.Employee;

public interface EmployeeService {
    public Employee login(Employee emp) throws Exception;

    public Employee getManager(Employee emp);
    
    public Employee getBoss();
    
    public Employee getTeller();

    public Employee getEmployee(String sn);
}
