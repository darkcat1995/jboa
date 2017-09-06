package com.jbit.jboa.service.impl;

import java.util.List;

import com.jbit.jboa.dao.EmployeeDao;
import com.jbit.jboa.entity.Department;
import com.jbit.jboa.entity.Employee;
import com.jbit.jboa.service.EmployeeService;

/**
 * @author ��������
 * �û�ҵ���࣬���ڵ���DAO����Ӧ����
 * 
 */
public class EmployeeServiceImpl implements EmployeeService {
	EmployeeDao empDao = null;
	
	//�û���¼�������õĲ�ѯ����
	public Employee login(Employee emp)throws Exception {
		Employee ret=null;
		List<Employee> list = this.empDao.findEmployee(emp);
		if(null!=list && list.size()>0){
			ret=list.get(0);
		}else{
			throw new Exception("�û������������");
		}
		return ret;
	}

	//��ѯ���ž���ķ���
	public Employee getManager(Employee emp) {
		Employee ret=null;
		Department department=null;
		if(null!=emp){
			ret=this.empDao.get(emp.getSn());
			department=this.empDao.get(ret.getSysDepartment().getId());
			ret=this.empDao.get(department.getSysEmployee().getSn());
		}
		return ret;
	}

	//��ѯboss�ķ���
    public Employee getBoss() {
        Employee ret=null;
        Department department=null;
        department=this.empDao.get(1);
        Employee e = new Employee();
        e.setSysDepartment(department);
        ret=this.empDao.findEmployee(e).get(0);
        return ret;
    }
    
    //��ѯ���ɵķ���
    public Employee getTeller(){
        Employee ret=null;
        Department department=null;
        department=this.empDao.get(2);
        Employee e = new Employee();
        e.setSysDepartment(department);
        ret=this.empDao.findEmployee(e).get(0);
        return ret;
    }

	//����Ա����Ų�ѯԱ����Ϣ
	public Employee getEmployee(String sn) {
		List<Employee> list=this.empDao.findManager(sn);
		if(list.size()>0){
		    return (Employee) list.get(0);
		}
		else{
			return null;
		}
	}
	public EmployeeDao getEmpDao() {
		return empDao;
	}

	public void setEmpDao(EmployeeDao empDao) {
		this.empDao = empDao;
	}
}
