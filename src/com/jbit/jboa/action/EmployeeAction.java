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
 * @author 北大青鸟 
 * 该类主要用于处理用户登录和登出操作
 * 
 */
public class EmployeeAction extends ActionSupport {
    private static final long serialVersionUID = 1L;

    private final Log logger = LogFactory.getLog(getClass());

    private Employee employee; // 用户类

    private EmployeeService empService;// 用户业务类

    private String msg; // 返回给页面的错误信息

    private String rand; // 表单中的验证码

    /**
     * 用户登录方法
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
                msg = "用户名密码不对，请重填";
                ret = INPUT;
            } else {
                ac.getSession().put("employee", newEmployee);
                // 获取职员职务信息，并获得职务名称
                String nameCn = newEmployee.getSysPosition().getNameCn();
                if ("员工".equals(nameCn)) {
                    ret = "staff";
                    ClaimVoucher claimVoucher = new ClaimVoucher();
                    claimVoucher.setId(0l);
                    claimVoucher.setStatus(Constants.CLAIMVOUCHER_CREATED);
                    ac.getSession().put("claimVoucher", claimVoucher);
                } else if ("部门经理".equals(nameCn)) {
                    ret = "deptManager";
                } else if ("总经理".equals(nameCn)) {
                    ret = "manager";
                } else if ("财务".equals(nameCn)) {
                    ret = "cashier";
                }
            }
        } else {
            msg = "验证码不正确";
            this.addActionMessage("验证码错误");
            ret = INPUT;
        }
        if (null != msg && !"".equals(msg)) {
            ac.put("msg", msg);
        }
        return ret;
    }

    /**
     * 用户退出。
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
