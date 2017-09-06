package com.jbit.jboa.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jbit.jboa.constants.Constants;
import com.jbit.jboa.entity.CheckResult;
import com.jbit.jboa.entity.ClaimVoucherDetail;
import com.jbit.jboa.entity.ClaimVoucher;
import com.jbit.jboa.entity.Employee;
import com.jbit.jboa.service.CheckResultService;
import com.jbit.jboa.service.ClaimVoucherDetailService;
import com.jbit.jboa.service.ClaimVoucherService;
import com.jbit.jboa.service.EmployeeService;
import com.jbit.jboa.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author 北大青鸟 
 * 该类主要用于处理页面请求过来的报销单的增加，修改，查询，删除调用操作
 */
public class ClaimVoucherAction extends ActionSupport {
    private static final long serialVersionUID = 1L;

    private final Log logger = LogFactory.getLog(getClass());

    private ClaimVoucherService claimVoucherService;

    private ClaimVoucherDetailService claimVoucherDetailService;

    private EmployeeService employeeService;

    private CheckResultService checkResultService;

    private ClaimVoucher claimVoucher;

    private ClaimVoucherDetail claimVoucherDetail;

    private Employee employee;

    private int page = 1;// 第几页

    private String type;

    private int currentGroup = 1;

    private PageBean pageBean;// 包含分布信息的bean

    /**
     * 负责将报销单和明细保 存到对应的表中，其中报销单中 的待处理人应该是填写人本人，
     * 保存的报销单不能被部门经理审核
     */
    public String save() {
//        ActionContext ac = ActionContext.getContext();
//        employee = (EmployeeEntity) ac.getSession().get("employee");
//        claimVoucher.setCreator(employee);
//        claimVoucher.setNextDealBy(employee);
        setCurrentEmployee();
        setClaimVoucher(employee);
        this.claimVoucherService.modifyClaimVoucher(claimVoucher,claimVoucherDetail, "save");
        return SUCCESS;
    }

    /**
     * 添加报销单详细信息
     * 
     */
    public String addDetail() {
        ActionContext ac = ActionContext.getContext();
        if(0 == claimVoucher.getId()){
//            employee = (EmployeeEntity) ac.getSession().get("employee");
//            claimVoucher.setCreator(employee);
//            claimVoucher.setNextDealBy(employee);
            setCurrentEmployee();
            setClaimVoucher(employee);
        }
        claimVoucherDetail.setBizClaimVoucher(claimVoucher);
        // 修改详细信息
        if (null != claimVoucherDetail.getId()
                && !"".equals(claimVoucherDetail.getId())
                && 0 != claimVoucherDetail.getId()) {
//            claimVoucherDetail.setBizClaimVoucher(claimVoucher);
            claimVoucher = claimVoucherDetailService.modifyClaimVoucherDetail(claimVoucherDetail);
//        } else if (0 != claimVoucher.getId()) {// 这种情况是当删除详细报销单到0个时，
            // 那么这时再增加详细报销单应该已经有Id了
//            claimVoucherDetail.setBizClaimVoucher(claimVoucher);
//            claimVoucher = claimVoucherDetailService.addClaimVoucherDetail(claimVoucherDetail);
        } else {// 添加详细信息
//            employee = (EmployeeEntity) ac.getSession().get("employee");
//            claimVoucher.setCreator(employee);
//            claimVoucher.setNextDealBy(employee);
//            claimVoucherDetail.setBizClaimVoucher(claimVoucher);
            claimVoucher = claimVoucherDetailService.addClaimVoucherDetail(claimVoucherDetail);
        }
        ac.getSession().put("details", claimVoucher.getBizClaimVoucherDetails());
        ac.getSession().put("claimVoucher", claimVoucher);
        return INPUT;
    }

    // 提交报销单
    public String submit(){
//        ActionContext ac = ActionContext.getContext();
//        employee = (EmployeeEntity) ac.getSession().get("employee");
//        claimVoucher.setCreator(employee);
//        claimVoucher.setNextDealBy(employee.getSysDepartment().getSysEmployee());
        setCurrentEmployee();
        setClaimVoucher(employee.getSysDepartment().getSysEmployee());
        this.claimVoucherService.modifyClaimVoucher(claimVoucher,claimVoucherDetail, "submit");
        return SUCCESS;
    }

    // 审核人员（普通员工，部门经理，总经理，财务）进行查询
    public String searchClaimVoucher() {
        ActionContext ac = ActionContext.getContext();
//      if (null == employee || null == employee.getSn()) {
//        employee = (EmployeeEntity) ac.getSession().get("employee");
        setCurrentEmployee();
//      }
        ClaimVoucher newClaimVoucher = new ClaimVoucher();
        if (employee.getSysPosition().getNameCn().equals(Constants.POSITION_FM)) {// 部门经理查询
            newClaimVoucher.setStatus(Constants.CLAIMVOUCHER_CREATED);
            setPageBean1(newClaimVoucher);
        } else if (employee.getSysPosition().getNameCn().equals(Constants.POSITION_GM)
                || employee.getSysPosition().getNameCn().equals(Constants.POSITION_CASHIER)) {// 总经理和财务查询
            newClaimVoucher.setNextDealBy(employee);
            setPageBean1(newClaimVoucher);
        } else if (employee.getSysPosition().getNameCn().equals(Constants.POSITION_STAFF)) {// 员工查询
            newClaimVoucher.setStatus(Constants.CLAIMVOUCHER_CREATED);
            setPageBean2(newClaimVoucher);
        }
        pageBean.setCurrentGroup(currentGroup);
        ac.getSession().put("pageBean", pageBean);
        return SUCCESS;
    }

    private void setPageBean1(ClaimVoucher newClaimVoucher){
        if (null != type && type.equals("group")) {
            pageBean = claimVoucherService.queryClaimVoucher(5, currentGroup * 5 - 4, newClaimVoucher, employee);
        } else if (null != type && type.equals("newSearch")) {// 2010/03/23
            // 新增一个按日期查询功能
            // dym
            newClaimVoucher.setCreateTime(claimVoucher.getCreateTime());
            pageBean = claimVoucherService.queryByDate(5, page, newClaimVoucher, employee);
        } else {
            pageBean = claimVoucherService.queryClaimVoucher(5, page, newClaimVoucher, employee);
        }
    }

    private void setPageBean2(ClaimVoucher newClaimVoucher){
        if (null != type && type.equals("group")) {
            pageBean = claimVoucherService.queryForPage(5, currentGroup * 5 - 4, claimVoucher, employee);
        } else if (null != type && type.equals("newSearch")) {// 2010/03/23
            // 新增一个按日期查询功能
            // dym
            newClaimVoucher.setCreateTime(claimVoucher.getCreateTime());
            pageBean = claimVoucherService.queryByDate(5, page, newClaimVoucher, employee);
        } else {
            pageBean = claimVoucherService.queryForPage(5, page, newClaimVoucher, employee);
        }
    }

    // 跳转到审核页面
    public String toCheck() {
        ActionContext ac = ActionContext.getContext();
        claimVoucher = this.claimVoucherService.query(claimVoucher);
        List<CheckResult> checkResultList = this.checkResultService.querySheetId(claimVoucher.getId());
//        EmployeeEntity manager = this.employeeService.getEmployee("JV100210002");
        Employee manager = this.employeeService.getManager((Employee)ac.getSession().get("employee"));
//        EmployeeEntity boss = this.employeeService.getEmployee("JV100210001");
        Employee boss = this.employeeService.getBoss();

        ac.getSession().put("checkResultList", checkResultList);
        ac.getSession().put("claimVoucher", claimVoucher);
        ac.getSession().put("manager", manager);
        ac.getSession().put("boss", boss);
        return SUCCESS;
    }

    // 删除报销单
    public String deletClaimVoucher() {
        this.claimVoucherService.removeClaimVoucher(claimVoucher);
        return SUCCESS;
    }

    // 删除报销单详细信息
    public String deleteClaimVoucherDetial() {
        ActionContext ac = ActionContext.getContext();
        claimVoucherDetail.setBizClaimVoucher(claimVoucher);
        claimVoucher = this.claimVoucherDetailService.removeClaimVoucherDetail(claimVoucherDetail);
        ac.getSession().put("details", claimVoucher.getBizClaimVoucherDetails());
        ac.getSession().put("claimVoucher", claimVoucher);
        return SUCCESS;
    }

    // 查看报销单
    public String viewClaimVoucher() {
        claimVoucher = this.claimVoucherService.query(claimVoucher);
        ActionContext ac = ActionContext.getContext();
        ac.getSession().put("claimVoucher", claimVoucher);
        return SUCCESS;
    }

    // 编辑报销单
    public String editClaimVoucher() {
        claimVoucher = this.claimVoucherService.query(claimVoucher);
        ActionContext ac = ActionContext.getContext();
        ac.getSession().put("details", claimVoucher.getBizClaimVoucherDetails());
        ac.getSession().put("claimVoucher", claimVoucher);
        return SUCCESS;
    }

    // 编辑报销单详细信息
    public String editClaimVoucherDetial() {
        claimVoucherDetail = this.claimVoucherDetailService.getClaimVoucherDetail(claimVoucherDetail, claimVoucher);
        claimVoucher = claimVoucherDetail.getBizClaimVoucher();
        ActionContext ac = ActionContext.getContext();
        ac.getSession().put("claimVoucher", claimVoucher);
        ac.getSession().put("claimVoucherDetail", claimVoucherDetail);
        return SUCCESS;
    }

    public ClaimVoucher getClaimVoucher() {
        return claimVoucher;
    }

    public void setClaimVoucher(ClaimVoucher claimVoucher) {
        this.claimVoucher = claimVoucher;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public ClaimVoucherDetail getClaimVoucherDetail() {
        return claimVoucherDetail;
    }

    public void setClaimVoucherDetail(
            ClaimVoucherDetail claimVoucherDetail) {
        this.claimVoucherDetail = claimVoucherDetail;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Log getLogger() {
        return logger;
    }

    public ClaimVoucherDetailService getClaimVoucherDetailService() {
        return claimVoucherDetailService;
    }

    public void setClaimVoucherDetailService(
            ClaimVoucherDetailService claimVoucherDetailService) {
        this.claimVoucherDetailService = claimVoucherDetailService;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public ClaimVoucherService getClaimVoucherService() {
        return claimVoucherService;
    }

    public void setClaimVoucherService(ClaimVoucherService claimVoucherService) {
        this.claimVoucherService = claimVoucherService;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public CheckResultService getCheckResultService() {
        return checkResultService;
    }

    public void setCheckResultService(CheckResultService checkResultService) {
        this.checkResultService = checkResultService;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCurrentGroup() {
        return currentGroup;
    }

    public void setCurrentGroup(int currentGroup) {
        this.currentGroup = currentGroup;
    }
    
    private void setCurrentEmployee(){
        ActionContext ac = ActionContext.getContext();
        employee = (Employee) ac.getSession().get("employee");
    }
    
    private void setClaimVoucher(Employee nextDealBy){
        claimVoucher.setCreator(employee);
        claimVoucher.setNextDealBy(nextDealBy);
    }
}
