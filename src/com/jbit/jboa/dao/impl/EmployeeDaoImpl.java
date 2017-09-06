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
 * @author ��������
 * �����û������û���ɾ�Ĳ����
 */
public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDao {

    //�����û����û����������ѯ�û�
    @SuppressWarnings("unchecked")
    public List<Employee> findEmployee(Employee emp) {
        List<Employee> list = super.getHibernateTemplate().findByExample(emp);
        return list;
    }

    // �����û���Ų�ѯ���û���Ϣ
    @SuppressWarnings("unchecked")
    public List<Employee> findManager(String sn) {
        String hql = "from Employee em where  em.sn='" + sn + "'";
        List<Employee> list = super.getHibernateTemplate().find(hql);
        return list;
    }

    // �����û���Ų�ѯ���û���Ϣ
    public Employee get(String sn) {
        return (Employee) this.getHibernateTemplate().get(
                Employee.class, sn);
    }

    // �����û���Ų�ѯ��������Ϣ
    public Department get(Integer sn) {
        return (Department) this.getHibernateTemplate().get(
                Department.class, sn);
    }

}
