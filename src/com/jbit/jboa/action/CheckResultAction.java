/**
 * 
 */
package com.jbit.jboa.action;

import com.jbit.jboa.constants.Constants;
import com.jbit.jboa.entity.CheckResult;
import com.jbit.jboa.entity.ClaimVoucher;
import com.jbit.jboa.entity.Employee;
import com.jbit.jboa.service.CheckResultService;
import com.jbit.jboa.service.ClaimVoucherService;
import com.jbit.jboa.service.EmployeeService;
import com.jbit.jboa.util.Util;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author 北大青鸟
 * 
 */
public class CheckResultAction extends ActionSupport {

    private static final long serialVersionUID = 4101941574314223772L;

    private ClaimVoucher claimVoucher;

    private CheckResult checkResult;

    private Employee employee;

    private CheckResultService checkResultService;

    private EmployeeService employeeService;

    private ClaimVoucherService claimVoucherService;

    public ClaimVoucherService getClaimVoucherService() {
        return claimVoucherService;
    }

    public void setClaimVoucherService(ClaimVoucherService claimVoucherService) {
        this.claimVoucherService = claimVoucherService;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // 审核报销单action,审核通过
    public String passClaimVoucher() {
//      ActionContext ac = ActionContext.getContext();
//      employee = (EmployeeEntity) ac.getSession().get("employee");
      Employee staff = getStaff();//new EmployeeEntity();
//      staff = this.employeeService.getEmployee(claimVoucher.getCreator().getSn());
//      claimVoucher = this.claimVoucherService.query(claimVoucher);
      init();
        if (employee.getSysPosition().getNameCn().equals(Constants.POSITION_FM)// 部门经理审核操作
                && staff.getSysDepartment().getId().equals(employee.getSysDepartment().getId())
                && employee.getSn().equals(claimVoucher.getNextDealBy().getSn())) {
            Employee nextDeal = new Employee();
            if (claimVoucher.getTotalAccount() > 5000) {
                claimVoucher.setStatus(Constants.CLAIMVOUCHER_SUBMITTED);
                //EmployeeEntity nextDeal = new EmployeeEntity();
                nextDeal = this.employeeService.getBoss();//getEmployee("JV100210001");// JV100210001是总经理编号
                //claimVoucher.setNextDealBy(nextDeal);
            } else {
                claimVoucher.setStatus(Constants.CLAIMVOUCHER_APPROVED);
                //EmployeeEntity nextDeal = new EmployeeEntity();
                nextDeal = this.employeeService.getTeller();//getEmployee("JV100210005");// JV100210001是总经理编号
                //claimVoucher.setNextDealBy(nextDeal);
            }
            claimVoucher.setNextDealBy(nextDeal);
//            checkResult.setResult(Constants.CHECKRESULT_PASS);
//            checkResult.setSheetId(claimVoucher.getId());
//            checkResult.setSheetType("报销单");
//            checkResult.setCheckTime(Util.parseDate(Util.now()));
//            checkResult.setSysEmployee(employee);
//            checkResult.setType(Constants.CHECKR_FM);
//            this.checkResultService.addCheckResult(checkResult, claimVoucher);
            setCheckResult(Constants.CHECKRESULT_PASS, Constants.CHECKR_FM);
        } else if (employee.getSysPosition().getNameCn().equals(Constants.POSITION_GM)
                && employee.getSn().equals(claimVoucher.getNextDealBy().getSn())) {// 总经理审核操作
            claimVoucher.setStatus(Constants.CLAIMVOUCHER_APPROVED);
            Employee nextDeal = new Employee();
            nextDeal = this.employeeService.getTeller();//getEmployee("JV100210005");
            claimVoucher.setNextDealBy(nextDeal);
//            checkResult.setResult(Constants.CHECKRESULT_PASS);
//            checkResult.setSheetId(claimVoucher.getId());
//            checkResult.setSheetType("报销单");
//            checkResult.setCheckTime(Util.parseDate(Util.now()));
//            checkResult.setSysEmployee(employee);
//            checkResult.setType(Constants.CHECKR_GM);
//            this.checkResultService.addCheckResult(checkResult, claimVoucher);
            setCheckResult(Constants.CHECKRESULT_PASS, Constants.CHECKR_GM);
        } else if (employee.getSysPosition().getNameCn().equals(Constants.POSITION_CASHIER)
                && employee.getSn().equals(claimVoucher.getNextDealBy().getSn())) {// 财务审核操作
            claimVoucher.setStatus(Constants.CLAIMVOUCHER_PAID);
            claimVoucher.setNextDealBy(null);
//            checkResult.setResult(Constants.CHECKRESULT_PAID);
//            checkResult.setSheetId(claimVoucher.getId());
//            checkResult.setSheetType("报销单");
//            checkResult.setCheckTime(Util.parseDate(Util.now()));
//            checkResult.setSysEmployee(employee);
//            checkResult.setType(Constants.CHECKR_CASHIER);
//            this.checkResultService.addCheckResult(checkResult, claimVoucher);
            setCheckResult(Constants.CHECKRESULT_PAID, Constants.CHECKR_CASHIER);
        } else {
            return INPUT;
        }
        return SUCCESS;
    }
    private void setCheckResult(String checkresult, String checkr){
        checkResult.setResult(checkresult);
        checkResult.setSheetId(claimVoucher.getId());
        checkResult.setSheetType("报销单");
        checkResult.setCheckTime(Util.parseDate(Util.now()));
        checkResult.setSysEmployee(employee);
        checkResult.setType(checkr);
        this.checkResultService.addCheckResult(checkResult, claimVoucher);
    }

    // 审核报销单action,审核拒绝
    public String registClaimVoucher() {
//        ActionContext ac = ActionContext.getContext();
//        employee = (EmployeeEntity) ac.getSession().get("employee");
        Employee staff = getStaff();//new EmployeeEntity();
//        staff = this.employeeService.getEmployee(claimVoucher.getCreator().getSn());
//        claimVoucher = this.claimVoucherService.query(claimVoucher);
        init();
        if (employee.getSysPosition().getNameCn().equals(Constants.POSITION_FM)
                && staff.getSysDepartment().getId().equals(employee.getSysDepartment().getId())
                && employee.getSn().equals(claimVoucher.getNextDealBy().getSn())) {// 部门经理审核操作
//            claimVoucher.setStatus(Constants.CLAIMVOUCHER_TERMINATED);
//            claimVoucher.setNextDealBy(null);
//            checkResult.setResult(Constants.CHECKRESULT_PASS);
//            checkResult.setSheetId(claimVoucher.getId());
//            checkResult.setSheetType("报销单");
//            checkResult.setCheckTime(Util.parseDate(Util.now()));
//            checkResult.setSysEmployee(employee);
//            checkResult.setType(Constants.CHECKR_FM);
//            this.checkResultService.addCheckResult(checkResult, claimVoucher);
            setCheckResult1(Constants.CHECKRESULT_PASS,Constants.CHECKR_FM);
        } else if (employee.getSysPosition().getNameCn().equals(Constants.POSITION_GM)
                && employee.getSn().equals(claimVoucher.getNextDealBy().getSn())) {// 总经理审核操作
//            claimVoucher.setStatus(Constants.CLAIMVOUCHER_TERMINATED);
//            claimVoucher.setNextDealBy(null);
//            checkResult.setResult(Constants.CHECKRESULT_BACK);
//            checkResult.setSheetId(claimVoucher.getId());
//            checkResult.setSheetType("报销单");
//            checkResult.setCheckTime(Util.parseDate(Util.now()));
//            checkResult.setSysEmployee(employee);
//            checkResult.setType(Constants.CHECKR_GM);
//            this.checkResultService.addCheckResult(checkResult, claimVoucher);
            setCheckResult1(Constants.CHECKRESULT_BACK,Constants.CHECKR_GM);
        } else {
            return INPUT;
        }
        return SUCCESS;
    }
    private void setCheckResult1(String checkresult,String checkr){
        claimVoucher.setStatus(Constants.CLAIMVOUCHER_TERMINATED);
        claimVoucher.setNextDealBy(null);
        checkResult.setResult(checkresult);
        checkResult.setSheetId(claimVoucher.getId());
        checkResult.setSheetType("报销单");
        checkResult.setCheckTime(Util.parseDate(Util.now()));
        checkResult.setSysEmployee(employee);
        checkResult.setType(checkr);
        this.checkResultService.addCheckResult(checkResult, claimVoucher);
    }

    // 审核报销单action,审核返回
    public String returnClaimVoucher() {
//      ActionContext ac = ActionContext.getContext();
//      employee = (EmployeeEntity) ac.getSession().get("employee");
      Employee staff = getStaff();//new EmployeeEntity();
//      staff = this.employeeService.getEmployee(claimVoucher.getCreator().getSn());
//      claimVoucher = this.claimVoucherService.query(claimVoucher);
      init();
        if (employee.getSysPosition().getNameCn().equals(Constants.POSITION_FM)
                && staff.getSysDepartment().getId().equals(employee.getSysDepartment().getId())
                && employee.getSn().equals(claimVoucher.getNextDealBy().getSn())) {// 部门经理审核操作
//            claimVoucher.setStatus(Constants.CLAIMVOUCHER_BACK);
//            claimVoucher.setNextDealBy(staff);
//            checkResult.setResult(Constants.CHECKRESULT_BACK);
//            checkResult.setSheetId(claimVoucher.getId());
//            checkResult.setSheetType("报销单");
//            checkResult.setCheckTime(Util.parseDate(Util.now()));
//            checkResult.setSysEmployee(employee);
//            checkResult.setType(Constants.CHECKR_FM);
//            this.checkResultService.addCheckResult(checkResult, claimVoucher);
            setCheckResult2(staff, Constants.CHECKR_FM);
        } else if (employee.getSysPosition().getNameCn().equals(Constants.POSITION_GM)
                && employee.getSn().equals(claimVoucher.getNextDealBy().getSn())) {// 总经理审核操作
//            claimVoucher.setStatus(Constants.CLAIMVOUCHER_BACK);
//            claimVoucher.setNextDealBy(staff);
//            checkResult.setResult(Constants.CHECKRESULT_BACK);
//            checkResult.setSheetId(claimVoucher.getId());
//            checkResult.setSheetType("报销单");
//            checkResult.setCheckTime(Util.parseDate(Util.now()));
//            checkResult.setSysEmployee(employee);
//            checkResult.setType(Constants.CHECKR_GM);
//            this.checkResultService.addCheckResult(checkResult, claimVoucher);
            setCheckResult2(staff, Constants.CHECKR_GM);
        } else {
            return INPUT;
        }

        return SUCCESS;
    }
    
    private void setCheckResult2(Employee staff, String checkr){
        claimVoucher.setStatus(Constants.CLAIMVOUCHER_BACK);
        claimVoucher.setNextDealBy(staff);
        checkResult.setResult(Constants.CHECKRESULT_BACK);
        checkResult.setSheetId(claimVoucher.getId());
        checkResult.setSheetType("报销单");
        checkResult.setCheckTime(Util.parseDate(Util.now()));
        checkResult.setSysEmployee(employee);
        checkResult.setType(checkr);
        this.checkResultService.addCheckResult(checkResult, claimVoucher);
    }
    
    private Employee getStaff(){
        return this.employeeService.getEmployee(claimVoucher.getCreator().getSn());
    }
    private void init(){
        ActionContext ac = ActionContext.getContext();
        employee = (Employee) ac.getSession().get("employee");
        claimVoucher = this.claimVoucherService.query(claimVoucher);
    }

    public CheckResult getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(CheckResult checkResult) {
        this.checkResult = checkResult;
    }

    public CheckResultService getCheckResultService() {
        return checkResultService;
    }

    public void setCheckResultService(CheckResultService checkResultService) {
        this.checkResultService = checkResultService;
    }

    public ClaimVoucher getClaimVoucher() {
        return claimVoucher;
    }

    public void setClaimVoucher(ClaimVoucher claimVoucher) {
        this.claimVoucher = claimVoucher;
    }

}
