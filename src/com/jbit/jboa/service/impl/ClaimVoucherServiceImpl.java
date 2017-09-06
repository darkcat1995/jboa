/**
 * 
 */
package com.jbit.jboa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.jbit.jboa.constants.Constants;
import com.jbit.jboa.dao.ClaimVoucherDao;
import com.jbit.jboa.dao.ClaimVoucherDetailDao;
import com.jbit.jboa.dao.EmployeeDao;
import com.jbit.jboa.entity.ClaimVoucherDetail;
import com.jbit.jboa.entity.ClaimVoucher;
import com.jbit.jboa.entity.Employee;
import com.jbit.jboa.service.ClaimVoucherService;
import com.jbit.jboa.util.PageBean;

/**
 * @author 北大青鸟
 * 报销单审核业务类，用于调用DAO类相应方法
 */
public class ClaimVoucherServiceImpl implements ClaimVoucherService {
    private ClaimVoucherDao claimVoucherDao;

    private EmployeeDao employeeDao;

    private ClaimVoucherDetailDao claimVoucherDetailDao;

    // 删除报销
    public void removeClaimVoucher(ClaimVoucher claimVoucher) {
        this.claimVoucherDao.deletClaimVoucher(claimVoucher);
    }

    // 查看报销单信息
    public ClaimVoucher query(ClaimVoucher claimVoucher) {
        claimVoucher = this.claimVoucherDao.getClaimVoucher(claimVoucher);
        return claimVoucher;
    }

    // 员工查询报销单方法
    public PageBean queryForPage(int pageSize, int page,
            ClaimVoucher condition, Employee employee) {
        StringBuffer sb_hql = new StringBuffer("from ClaimVoucher c ");// 查询语句
        String hql;

        if (condition != null) {
            sb_hql.append("where");
            if (!"".equals(employee.getSn())) {
                sb_hql.append("  c.creator.sn=:creatorSn order by c.status asc c.createTime desc and  ");
            }
            String temp = sb_hql.toString();
            if (temp.contains("and")) {
                hql = temp.substring(0, temp.lastIndexOf("and")).trim();
            } else {
                hql = temp.substring(0, temp.lastIndexOf("where")).trim();
            }
        } else {
            hql = sb_hql.toString();
        }
        System.out.println(hql);
        String countHql = "select count(c.id) " + hql.substring(0, hql.lastIndexOf("order")).trim();
        System.out.println(countHql);

        int allRow = claimVoucherDao.getAllRowCount(countHql, condition, employee, null);// 总记录数
        int totalPage = PageBean.countTotalPage(pageSize, allRow);// 总页数
        final int offset = PageBean.countOffset(pageSize, page);// 当前页开始记录
        final int length = pageSize;// 每页记录数
        final int currentPage = PageBean.countCurrentPage(page);
        final int totalGroup = PageBean.countTotalGroup(totalPage);
        List<ClaimVoucher> list = claimVoucherDao.queryForPage(hql,
                offset, length, condition, employee, null);// "一页"的记录

        // 把分页信息保存到Bean中
        return setPageBean(pageSize,currentPage,allRow,totalPage,list,totalGroup);
    }

    // 按日期查询功能
    public PageBean queryByDate(int pageSize, int page,
            ClaimVoucher condition, Employee employee) {
        StringBuffer sb_hql = new StringBuffer(
                "select distinct c from ClaimVoucher c,Employee e ");// 查询语句
        String hql = null;
        if (employee != null) {
            if (employee.getSysPosition().getNameCn().equals(
                    Constants.POSITION_FM)) {
                sb_hql.append("where");
                if (!"".equals(employee.getSn()) && null != employee.getSn()) {
                    sb_hql.append("  e.sysDepartment.id=:departmentId  and ");
                }
                if (!"".equals(condition.getCreateTime())) {// 增加按日期查询条件
                    sb_hql.append("    c.createTime between :date1 and :date2  and");
                }
                if (!"".equals(condition.getStatus())) {
                    sb_hql.append(" c.status<>:status order by c.status asc c.createTime desc and");
                }
            } else if (employee.getSysPosition().getNameCn().equals(
                    Constants.POSITION_GM)
                    || employee.getSysPosition().getNameCn().equals(
                            Constants.POSITION_CASHIER)) {
                sb_hql.append(" ,CheckResult cr where");
                if (!"".equals(employee.getSn()) && null != employee.getSn()) {
                    sb_hql.append("  c.nextDealBy.sn=e.sn and e.sn=:nextDealSn or (cr.sheetId=c.id and cr.sysEmployee.sn=e.sn and e.sn=:nextDealSn) ");
                }
                if (!"".equals(condition.getCreateTime())) {// 增加按日期查询条件
                    sb_hql.append(" and   c.createTime between :date1 and :date2  ");
                }
                if (1 == 1) {
                    sb_hql.append(" order by c.status asc c.createTime desc and");
                }
            } else if (employee.getSysPosition().getNameCn().equals(// 增加按日期查询功能
                    Constants.POSITION_STAFF)) {
                if (condition != null) {
                    sb_hql.append("where");
                    if (!"".equals(condition.getCreateTime())) {
                        sb_hql.append("  c.createTime between :date1 and :date2  and");
                    }
                    if (!"".equals(employee.getSn())) {
                        sb_hql.append("  c.creator.sn=:creatorSn order by c.status asc c.createTime desc and  ");
                    }
                }
            }
            String temp = sb_hql.toString();
            if (temp.contains("and")) {
                hql = temp.substring(0, temp.lastIndexOf("and")).trim();
            } else {
                hql = temp.substring(0, temp.lastIndexOf("where")).trim();
            }
        } else {
            hql = sb_hql.toString();
        }
        System.out.println(hql);
        String newhql = hql.substring(0, hql.lastIndexOf("order")).trim();
        int num = newhql.toUpperCase().indexOf("FROM");
        String countHql = "select count(distinct c.id) " + newhql.substring(num, newhql.length());
        System.out.println(newhql);
        int allRow = 0;
        if (employee.getSysPosition().getNameCn().equals(Constants.POSITION_STAFF)) {// 增加按日期查询功能
            allRow = claimVoucherDao.getAllRowCount(countHql, condition, employee, "date");
        } else {
            allRow = claimVoucherDao.getAllRowCountM(countHql, condition, employee, "date");// 总记录数
        }
        int totalPage = PageBean.countTotalPage(pageSize, allRow);// 总页数
        final int offset = PageBean.countOffset(pageSize, page);// 当前页开始记录
        final int length = pageSize;// 每页记录数
        final int currentPage = PageBean.countCurrentPage(page);
        final int totalGroup = PageBean.countTotalGroup(totalPage);
        List<ClaimVoucher> list = null;
        if (employee.getSysPosition().getNameCn().equals(Constants.POSITION_STAFF)) {// 增加按日期查询功能
            list = claimVoucherDao.queryForPage(hql, offset, length, condition, employee, "date");// 传的参数中有“date”，代表执行按日期查询 ，"一页"的记录
        } else {
            list = claimVoucherDao.queryForPageM(hql, offset, length, condition, employee, "date");// 传的参数中有“date”，代表执行按日期查询
        }
        // 把分页信息保存到Bean中
        return setPageBean(pageSize,currentPage,allRow,totalPage,list,totalGroup);
    }

    /**
     * @author 北大青鸟
     *  修改报销单， 分成两种情况，即保存和提交报销单，通过参数type进行区分
     *         保存时执行两个动作，（1）当在请求页面中已经填写了报销单信息
     *         ，数据库中没有此条信息时，执行保存到库中（2）当已经提交过了，此时再点保存只修改报销单处理人信息
     *         提交包含两个动作，（1）当在请求页面中已经填写了报销单信息
     *         ，同时也已经填写了报销单详细信息，数据库中没有此条报销单信息时，将报销单信息和报销单详细信息分别保存到库中，并更改处理人信息。
     *         （2）当在请求页面中已经填写了报销单信息，数据库中没有此条报销单信息时，点击提交只需将处理人信息修改为本部门的部门经理即可
     */
    public ClaimVoucher modifyClaimVoucher(
            ClaimVoucher claimVoucher,
            ClaimVoucherDetail claimVoucherDetail, String type){
        ClaimVoucher newClaimVoucher = null;
        if ("save".equals(type)) {
            newClaimVoucher = saveClaimVoucher(claimVoucher);
        }
        if ("submit".equals(type)) {
            newClaimVoucher = submitClaimVoucher(claimVoucher,claimVoucherDetail);
        }
        
        
        return newClaimVoucher;
    }

    // 部门经理查询已提交和审核后的报销单
    public PageBean queryClaimVoucher(int pageSize, int page,
            ClaimVoucher condition, Employee employee) {
    	// 查询语句
        StringBuffer sb_hql = new StringBuffer(
         "select distinct c from ClaimVoucher c,Employee e ");
        String hql = null;
        if (employee != null) {
        	// 当以部门经理身份登陆时执行的查询，
            // 查询本部门员工填写的不包含新创建的报销单
            if (employee.getSysPosition().getNameCn().equals(Constants.POSITION_FM)) {                                             
                sb_hql.append("where");
                if (!"".equals(employee.getSn()) && null != employee.getSn()) {
                    sb_hql.append("  e.sysDepartment.id=:departmentId  and ");
                }
                if (!"".equals(condition.getStatus())) {
                    sb_hql.append(" c.status<>:status order by c.status asc c.createTime desc and");
                }
            }
            // 当以总经理身份登陆时执行的查询，查询待审核人为当前登陆用户，
            // 或者当前登陆用户曾审核过
            else if (employee.getSysPosition().getNameCn().equals(Constants.POSITION_GM)                                          
                    || employee.getSysPosition().getNameCn().equals(Constants.POSITION_CASHIER)) {
                sb_hql.append(" ,CheckResult cr where");
                if (!"".equals(employee.getSn()) && null != employee.getSn()) {
                    sb_hql.append("  c.nextDealBy.sn=e.sn and e.sn=:nextDealSn " +
                    		"or (cr.sheetId=c.id and cr.sysEmployee.sn=e.sn and e.sn=:nextDealSn) " +
                    		"order by c.status asc c.createTime desc and");
                }
            }
            String temp = sb_hql.toString();
            if (temp.contains("and")) {
                hql = temp.substring(0, temp.lastIndexOf("and")).trim();
            } else {
                hql = temp.substring(0, temp.lastIndexOf("where")).trim();
            }
        } else {
            hql = sb_hql.toString();
        }
        System.out.println(hql);
        String newhql = hql.substring(0, hql.lastIndexOf("order")).trim();
        int num = newhql.toUpperCase().indexOf("FROM");
        String countHql = "select count(distinct c.id) " + newhql.substring(num, newhql.length());
        System.out.println(newhql);

        int allRow = claimVoucherDao.getAllRowCountM(countHql, condition, employee, null);// 总记录数
        int totalPage = PageBean.countTotalPage(pageSize, allRow);// 总页数
        final int offset = PageBean.countOffset(pageSize, page);// 当前页开始记录
        final int length = pageSize;// 每页记录数
        final int currentPage = PageBean.countCurrentPage(page);
        final int totalGroup = PageBean.countTotalGroup(totalPage);
        List<ClaimVoucher> list = claimVoucherDao
        	.queryForPageM(hql, offset, length, condition, employee, null);// "一页"的记录

        // 把分页信息保存到Bean中
        return setPageBean(pageSize,currentPage,allRow,totalPage,list,totalGroup);
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public ClaimVoucherDetailDao getClaimVoucherDetailDao() {
        return claimVoucherDetailDao;
    }

    public void setClaimVoucherDetailDao(
            ClaimVoucherDetailDao claimVoucherDetailDao) {
        this.claimVoucherDetailDao = claimVoucherDetailDao;
    }

    public ClaimVoucherDao getClaimVoucherDao() {
        return claimVoucherDao;
    }

    public void setClaimVoucherDao(ClaimVoucherDao claimVoucherDao) {
        this.claimVoucherDao = claimVoucherDao;
    }
    
    private ClaimVoucher saveClaimVoucher(
            ClaimVoucher claimVoucher){
        ClaimVoucher newClaimVoucher = null;
        if (null == claimVoucher.getId() || 0 == claimVoucher.getId()
                || "".equals(claimVoucher.getId())) {
            if (null == claimVoucher.getTotalAccount()
                    || "".equals(claimVoucher.getTotalAccount())) {
                claimVoucher.setTotalAccount(0d);
            }
            newClaimVoucher = this.claimVoucherDao
                    .saveClaimVoucher(claimVoucher);
        } else {
            newClaimVoucher = this.claimVoucherDao
                    .updateClaimVoucher(claimVoucher);
        }
        return newClaimVoucher;
    }
    
    private ClaimVoucher submitClaimVoucher(
            ClaimVoucher claimVoucher,
            ClaimVoucherDetail claimVoucherDetail){
        ClaimVoucher newClaimVoucher = null;
        if (claimVoucher.getId() != 0) {

            newClaimVoucher = this.claimVoucherDao
                    .getClaimVoucher(claimVoucher);
            // 修改报销单的下一个审核人
            newClaimVoucher.setNextDealBy(claimVoucher.getNextDealBy());
            // 修改报销申请单状态
            newClaimVoucher.setStatus(Constants.CLAIMVOUCHER_SUBMITTED);
            newClaimVoucher = claimVoucherDao
                    .updateClaimVoucher(newClaimVoucher);
        } else {
            List<ClaimVoucherDetail> detailList = new ArrayList<ClaimVoucherDetail>();
            detailList.add(claimVoucherDetail);
            if (null == claimVoucher.getTotalAccount()
                    || "".equals(claimVoucher.getTotalAccount())) {
                claimVoucher.setTotalAccount(0d);
            }
            newClaimVoucher = this.claimVoucherDao
                    .saveClaimVoucher(claimVoucher);
            claimVoucherDetail.setBizClaimVoucher(newClaimVoucher);
            this.claimVoucherDetailDao
                    .saveClaimVoucherDetail(claimVoucherDetail);
            newClaimVoucher
                    .setTotalAccount(claimVoucherDetail.getAccount());
            newClaimVoucher = this.claimVoucherDao
                    .updateClaimVoucher(newClaimVoucher);
            newClaimVoucher.setBizClaimVoucherDetails(this
                    .getClaimVoucherDetailDao().getDetailsByClaimVoucheId(
                            claimVoucher.getId()));
        }
        return newClaimVoucher;
    }

    // 把分页信息保存到Bean中
    private PageBean setPageBean(int pageSize,int currentPage,int allRow,int totalPage,List<ClaimVoucher> list,int totalGroup){
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setAllRow(allRow);
        pageBean.setTotalPage(totalPage);
        pageBean.setList(list);
        pageBean.setTotalGroup(totalGroup);
        pageBean.init();
        return pageBean;
    }
}
