/**
 * 
 */
package com.jbit.jboa.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jbit.jboa.constants.Constants;

import com.jbit.jboa.entity.ClaimVoucher;
import com.jbit.jboa.entity.Employee;
import com.jbit.jboa.service.EmployeeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author �������� 
 * ������Ҫ���ڴ����û���¼�͵ǳ�����
 * 
 */
public class EmployeeAction extends ActionSupport {
    private static final long serialVersionUID = 1L;

    private final Log logger = LogFactory.getLog(getClass());

    private Employee employee; // �û���

    private EmployeeService empService;// �û�ҵ����

    private String msg; // ���ظ�ҳ��Ĵ�����Ϣ

    private String rand; // ���е���֤��

    /**
     * �û���¼����
     */
    public String login() throws Exception {
        ActionContext ac = ActionContext.getContext();
        String random = (String) (ac.getSession().get("random"));
        Employee newEmployee = null;
        String ret = INPUT;
        if (random.equals(this.getRand())) {
            try {
                newEmployee = empService.login(employee);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            if (newEmployee == null) {
                msg = "�û������벻�ԣ�������";
                ret = INPUT;
            } else {
                ac.getSession().put("employee", newEmployee);
                // ��ȡְԱְ����Ϣ�������ְ������
                String nameCn = newEmployee.getSysPosition().getNameCn();
                if ("Ա��".equals(nameCn)) {
                    ret = "staff";
                    ClaimVoucher claimVoucher = new ClaimVoucher();
                    claimVoucher.setId(0l);
                    claimVoucher.setStatus(Constants.CLAIMVOUCHER_CREATED);
                    ac.getSession().put("claimVoucher", claimVoucher);
                } else if ("���ž���".equals(nameCn)) {
                    ret = "deptManager";
                } else if ("�ܾ���".equals(nameCn)) {
                    ret = "manager";
                } else if ("����".equals(nameCn)) {
                    ret = "cashier";
                }
            }
        } else {
            msg = "��֤�벻��ȷ";
            this.addActionMessage("��֤�����");
            ret = INPUT;
        }
        if (null != msg && !"".equals(msg)) {
            ac.put("msg", msg);
        }
        return ret;
    }

    /**
     * �û��˳���
     */
    public String loginOut() throws Exception {
        ActionContext ac = ActionContext.getContext();
        ac.getSession().clear();
        return SUCCESS;
    }

    public String getRand() {
        return rand;
    }

    public void setRand(String rand) {
        this.rand = rand;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public EmployeeService getEmpService() {
        return empService;
    }

    public void setEmpService(EmployeeService empService) {
        this.empService = empService;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
