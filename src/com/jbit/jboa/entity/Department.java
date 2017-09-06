package com.jbit.jboa.entity;

import java.util.HashSet;
import java.util.Set;
/**
 * 部门实体类。
 * @author 北大青鸟
 *
 */
@SuppressWarnings("unchecked")
public class Department implements java.io.Serializable {

    private static final long serialVersionUID = -4386159382927256061L;
    private Integer id;
    private Employee sysEmployee;
    private String name;
    private Set sysEmployees = new HashSet(0);

    /** default constructor */
    public Department() {
    }

    /** minimal constructor */
    public Department(String name) {
        this.name = name;
    }

    /** full constructor */
    public Department(Employee sysEmployee, String name,
            Set sysEmployees) {
        this.sysEmployee = sysEmployee;
        this.name = name;
        this.sysEmployees = sysEmployees;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getSysEmployee() {
        return this.sysEmployee;
    }

    public void setSysEmployee(Employee sysEmployee) {
        this.sysEmployee = sysEmployee;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set getSysEmployees() {
        return this.sysEmployees;
    }

    public void setSysEmployees(Set sysEmployees) {
        this.sysEmployees = sysEmployees;
    }

}