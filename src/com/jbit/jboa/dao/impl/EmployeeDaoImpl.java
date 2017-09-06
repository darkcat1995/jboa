/**
 * 
 */
package com.jbit.jboa.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jbit.jboa.dao.EmployeeDao;
import com.jbit.jboa.entity.Department;
import com.jbit.jboa.entity.Employee;

/**
 * @author 北大青鸟
 * 该类用户处理用户增删改查操作
 */
public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDao {

    //根据用户的用户名和密码查询用户
    @SuppressWarnings("unchecked")
    public List<Employee> findEmployee(Employee emp) {
        List<Employee> list = super.getHibernateTemplate().findByExample(emp);
        return list;
    }

    // 根据用户编号查询出用户信息
    @SuppressWarnings("unchecked")
    public List<Employee> findManager(String sn) {
        String hql = "from Employee em where  em.sn='" + sn + "'";
        List<Employee> list = super.getHibernateTemplate().find(hql);
        return list;
    }

    // 根据用户编号查询出用户信息
    public Employee get(String sn) {
        return (Employee) this.getHibernateTemplate().get(
                Employee.class, sn);
    }

    // 根据用户编号查询出部门信息
    public Department get(Integer sn) {
        return (Department) this.getHibernateTemplate().get(
                Department.class, sn);
    }

}
