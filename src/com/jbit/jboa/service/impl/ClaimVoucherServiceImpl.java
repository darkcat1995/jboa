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
 * @author ��������
 * ���������ҵ���࣬���ڵ���DAO����Ӧ����
 */
public class ClaimVoucherServiceImpl implements ClaimVoucherService {
    private ClaimVoucherDao claimVoucherDao;

    private EmployeeDao employeeDao;

    private ClaimVoucherDetailDao claimVoucherDetailDao;

    // ɾ������
    public void removeClaimVoucher(ClaimVoucher claimVoucher) {
        this.claimVoucherDao.deletClaimVoucher(claimVoucher);
    }

    // �鿴��������Ϣ
    public ClaimVoucher query(ClaimVoucher claimVoucher) {
        claimVoucher = this.claimVoucherDao.getClaimVoucher(claimVoucher);
        return claimVoucher;
    }

    // Ա����ѯ����������
    public PageBean queryForPage(int pageSize, int page,
            ClaimVoucher condition, Employee employee) {
        StringBuffer sb_hql = new StringBuffer("from ClaimVoucher c ");// ��ѯ���
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

        int allRow = claimVoucherDao.getAllRowCount(countHql, condition, employee, null);// �ܼ�¼��
        int totalPage = PageBean.countTotalPage(pageSize, allRow);// ��ҳ��
        final int offset = PageBean.countOffset(pageSize, page);// ��ǰҳ��ʼ��¼
        final int length = pageSize;// ÿҳ��¼��
        final int currentPage = PageBean.countCurrentPage(page);
        final int totalGroup = PageBean.countTotalGroup(totalPage);
        List<ClaimVoucher> list = claimVoucherDao.queryForPage(hql,
                offset, length, condition, employee, null);// "һҳ"�ļ�¼

        // �ѷ�ҳ��Ϣ���浽Bean��
        return setPageBean(pageSize,currentPage,allRow,totalPage,list,totalGroup);
    }

    // �����ڲ�ѯ����
    public PageBean queryByDate(int pageSize, int page,
            ClaimVoucher condition, Employee employee) {
        StringBuffer sb_hql = new StringBuffer(
                "select distinct c from ClaimVoucher c,Employee e ");// ��ѯ���
        String hql = null;
        if (employee != null) {
            if (employee.getSysPosition().getNameCn().equals(
                    Constants.POSITION_FM)) {
                sb_hql.append("where");
                if (!"".equals(employee.getSn()) && null != employee.getSn()) {
                    sb_hql.append("  e.sysDepartment.id=:departmentId  and ");
                }
                if (!"".equals(condition.getCreateTime())) {// ���Ӱ����ڲ�ѯ����
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
                if (!"".equals(condition.getCreateTime())) {// ���Ӱ����ڲ�ѯ����
                    sb_hql.append(" and   c.createTime between :date1 and :date2  ");
                }
                if (1 == 1) {
                    sb_hql.append(" order by c.status asc c.createTime desc and");
                }
            } else if (employee.getSysPosition().getNameCn().equals(// ���Ӱ����ڲ�ѯ����
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
        if (employee.getSysPosition().getNameCn().equals(Constants.POSITION_STAFF)) {// ���Ӱ����ڲ�ѯ����
            allRow = claimVoucherDao.getAllRowCount(countHql, condition, employee, "date");
        } else {
            allRow = claimVoucherDao.getAllRowCountM(countHql, condition, employee, "date");// �ܼ�¼��
        }
        int totalPage = PageBean.countTotalPage(pageSize, allRow);// ��ҳ��
        final int offset = PageBean.countOffset(pageSize, page);// ��ǰҳ��ʼ��¼
        final int length = pageSize;// ÿҳ��¼��
        final int currentPage = PageBean.countCurrentPage(page);
        final int totalGroup = PageBean.countTotalGroup(totalPage);
        List<ClaimVoucher> list = null;
        if (employee.getSysPosition().getNameCn().equals(Constants.POSITION_STAFF)) {// ���Ӱ����ڲ�ѯ����
            list = claimVoucherDao.queryForPage(hql, offset, length, condition, employee, "date");// ���Ĳ������С�date��������ִ�а����ڲ�ѯ ��"һҳ"�ļ�¼
        } else {
            list = claimVoucherDao.queryForPageM(hql, offset, length, condition, employee, "date");// ���Ĳ������С�date��������ִ�а����ڲ�ѯ
        }
        // �ѷ�ҳ��Ϣ���浽Bean��
        return setPageBean(pageSize,currentPage,allRow,totalPage,list,totalGroup);
    }

    /**
     * @author ��������
     *  �޸ı������� �ֳ������������������ύ��������ͨ������type��������
     *         ����ʱִ��������������1����������ҳ�����Ѿ���д�˱�������Ϣ
     *         �����ݿ���û�д�����Ϣʱ��ִ�б��浽���У�2�����Ѿ��ύ���ˣ���ʱ�ٵ㱣��ֻ�޸ı�������������Ϣ
     *         �ύ����������������1����������ҳ�����Ѿ���д�˱�������Ϣ
     *         ��ͬʱҲ�Ѿ���д�˱�������ϸ��Ϣ�����ݿ���û�д�����������Ϣʱ������������Ϣ�ͱ�������ϸ��Ϣ�ֱ𱣴浽���У������Ĵ�������Ϣ��
     *         ��2����������ҳ�����Ѿ���д�˱�������Ϣ�����ݿ���û�д�����������Ϣʱ������ύֻ�轫��������Ϣ�޸�Ϊ�����ŵĲ��ž�����
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

    // ���ž����ѯ���ύ����˺�ı�����
    public PageBean queryClaimVoucher(int pageSize, int page,
            ClaimVoucher condition, Employee employee) {
    	// ��ѯ���
        StringBuffer sb_hql = new StringBuffer(
         "select distinct c from ClaimVoucher c,Employee e ");
        String hql = null;
        if (employee != null) {
        	// ���Բ��ž�����ݵ�½ʱִ�еĲ�ѯ��
            // ��ѯ������Ա����д�Ĳ������´����ı�����
            if (employee.getSysPosition().getNameCn().equals(Constants.POSITION_FM)) {                                             
                sb_hql.append("where");
                if (!"".equals(employee.getSn()) && null != employee.getSn()) {
                    sb_hql.append("  e.sysDepartment.id=:departmentId  and ");
                }
                if (!"".equals(condition.getStatus())) {
                    sb_hql.append(" c.status<>:status order by c.status asc c.createTime desc and");
                }
            }
            // �����ܾ�����ݵ�½ʱִ�еĲ�ѯ����ѯ�������Ϊ��ǰ��½�û���
            // ���ߵ�ǰ��½�û�����˹�
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

        int allRow = claimVoucherDao.getAllRowCountM(countHql, condition, employee, null);// �ܼ�¼��
        int totalPage = PageBean.countTotalPage(pageSize, allRow);// ��ҳ��
        final int offset = PageBean.countOffset(pageSize, page);// ��ǰҳ��ʼ��¼
        final int length = pageSize;// ÿҳ��¼��
        final int currentPage = PageBean.countCurrentPage(page);
        final int totalGroup = PageBean.countTotalGroup(totalPage);
        List<ClaimVoucher> list = claimVoucherDao
        	.queryForPageM(hql, offset, length, condition, employee, null);// "һҳ"�ļ�¼

        // �ѷ�ҳ��Ϣ���浽Bean��
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
            // �޸ı���������һ�������
            newClaimVoucher.setNextDealBy(claimVoucher.getNextDealBy());
            // �޸ı������뵥״̬
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

    // �ѷ�ҳ��Ϣ���浽Bean��
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
