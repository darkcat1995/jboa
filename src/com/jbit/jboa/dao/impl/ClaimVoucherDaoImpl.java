/**
 * 
 */
package com.jbit.jboa.dao.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jbit.jboa.constants.Constants;
import com.jbit.jboa.dao.ClaimVoucherDao;
import com.jbit.jboa.entity.ClaimVoucherDetail;
import com.jbit.jboa.entity.ClaimVoucher;
import com.jbit.jboa.entity.Employee;
import com.jbit.jboa.util.Util;

/**
 * @author 北大青鸟 
 * 该类用于处理报销增删改查操作
 * 
 */
public class ClaimVoucherDaoImpl extends HibernateDaoSupport implements
        ClaimVoucherDao {

    /*
     * 根据报销单Id查询出报销单信息
     * 
     * @seecom.jbit.jboa.dao.ClaimVoucherDao#find(com.jbit.jboa.entity.
     * ClaimVoucherEntity)
     */
    @SuppressWarnings("unchecked")
    public List<ClaimVoucher> find(ClaimVoucher condition) {
        List<ClaimVoucher> list = super.getHibernateTemplate()
                .findByExample(condition);
        return list;
    }

    /*
     * 查询出报销单表中所有报销单信息
     */
    @SuppressWarnings("unchecked")
    public List<ClaimVoucher> findAll() {
        List<ClaimVoucher> list = super.getHibernateTemplate().find(
                "from ClaimVoucher ");
        return list;
    }

    /*
     * 员工提交或保存报销单后执行的查询方法
     * 当type的值为date时，表示要执行安日期进行查询
     */
    public List<ClaimVoucher> queryForPage(final String hql,
            final int offset, final int length,
            final ClaimVoucher condition, final Employee employee,
            final String type) {
        @SuppressWarnings("unchecked")
        List<ClaimVoucher> list = getHibernateTemplate().executeFind(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        Query query = session.createQuery(hql);

                        if (!"".equals(employee.getSn())) {
                            query.setString("creatorSn", employee.getSn());
                        }
                        setQueryDate(type, condition, query);

                        return getListForPage(query, offset, length);
                    }
                });
        return list;
    }

    /*
     * 部门经理查询报销单方法 当type的值为date时，表示要执行安日期进行查询
     */
    public List<ClaimVoucher> queryForPageM(final String hql,
            final int offset, final int length,
            final ClaimVoucher condition, final Employee employee,
            final String type) {
        @SuppressWarnings("unchecked")
        List<ClaimVoucher> list = getHibernateTemplate().executeFind(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        Query query = session.createQuery(hql);
                        if (employee.getSysPosition().getNameCn().equals(
                                Constants.POSITION_FM)) {
                            if (employee != null && !"".equals(employee.getSn())) {
                                query.setInteger("departmentId", employee.getSysDepartment().getId());
                            }
                            if (!"".equals(condition.getStatus())) {
                                query.setString("status", condition.getStatus());
                            }
                            setQueryDate(type, condition, query);
                        } else if (employee.getSysPosition().getNameCn().equals(Constants.POSITION_GM)
                                || employee.getSysPosition().getNameCn().equals(Constants.POSITION_CASHIER)) {
                            if (null != employee.getSn() && !"".equals(employee.getSn())) {
                                query.setString("nextDealSn", employee.getSn());
                            }
                            setQueryDate(type, condition, query);
                        }

                        return getListForPage(query, offset, length);
                    }
                });
        return list;
    }

    /*
     * 员工查询时得到所有行的方法 当type的值为date时，表示要执行安日期进行查询
     */
    public int getAllRowCount(final String hql,
            final ClaimVoucher condition, final Employee employee,
            final String type) {
        @SuppressWarnings("unchecked")
        List<String> totalNum = getHibernateTemplate().executeFind(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        Query query = session.createQuery(hql);

                        if (!"".equals(employee.getSn())) {
                            query.setString("creatorSn", employee.getSn());
                        }
                        setQueryDate(type, condition, query);

                        String num = query.uniqueResult().toString();
                        List<String> list = new ArrayList<String>();
                        list.add(num);
                        return list;
                    }
                });

        return Integer.parseInt(totalNum.get(0));
    }

    /*
     * 部门经理执行查询时掉用该方法查询出总条数 当type的值为date时，表示要执行安日期进行查询
     */
    public int getAllRowCountM(final String hql,
            final ClaimVoucher condition, final Employee employee,
            final String type) {
        @SuppressWarnings("unchecked")
        List<String> totalNum = getHibernateTemplate().executeFind(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        Query query = session.createQuery(hql);
                        if (employee.getSysPosition().getNameCn().equals(Constants.POSITION_FM)) {
                            if (null != employee.getSn() && !"".equals(employee.getSn())) {
                                query.setInteger("departmentId", employee.getSysDepartment().getId());
                            }
                            if (!"".equals(condition.getStatus())) {
                                query.setString("status", condition.getStatus());
                            }
                            setQueryDate(type, condition, query);
                        } else if (employee.getSysPosition().getNameCn().equals(Constants.POSITION_GM)
                                || employee.getSysPosition().getNameCn().equals(Constants.POSITION_CASHIER)) {
                            if (null != employee.getSn() && !"".equals(employee.getSn())) {
                                query.setString("nextDealSn", employee.getSn());
                            }
                            setQueryDate(type, condition, query);
                        }

                        String num = query.uniqueResult().toString();
                        List<String> list = new ArrayList<String>();
                        list.add(num);
                        return list;
                    }
                });

        return Integer.parseInt(totalNum.get(0));
    }

    // 保存报销单信息
    public ClaimVoucher saveClaimVoucher(ClaimVoucher claimVoucher) {
        super.getHibernateTemplate().save(claimVoucher);
        return claimVoucher;
    }

    // 更新报销单信息
    public ClaimVoucher updateClaimVoucher(ClaimVoucher claimVoucher) {
        super.getHibernateTemplate().update(claimVoucher);
        return claimVoucher;
    }

    // 删除报销单信息
    public void deletClaimVoucher(ClaimVoucher claimVoucher) {
        claimVoucher = (ClaimVoucher) super.getHibernateTemplate().get(
                ClaimVoucher.class, claimVoucher.getId());
        super.getHibernateTemplate().delete(claimVoucher);
    }

    // 根据报销单id得到报销单信息
    @SuppressWarnings("unchecked")
    public ClaimVoucher getClaimVoucher(
    		ClaimVoucher claimVoucher) {
        List<ClaimVoucherDetail> list = super.getHibernateTemplate()
                .find("from ClaimVoucherDetail c " +
                		"where c.bizClaimVoucher.id=" + claimVoucher.getId());
        super.getHibernateTemplate().refresh(claimVoucher);
        claimVoucher.setBizClaimVoucherDetails(list);
        return claimVoucher;
    }
    
    private void setQueryDate(final String type,final ClaimVoucher condition, Query query){
        if (null != type && !"".equals(condition.getCreateTime())) {
            String date = condition.getCreateTime().toString().substring(0, 7);
            String date1 = date + "-01";
            String date2 = date + "-31";
            System.out.println("date1=" + date1);
            System.out.println("date2=" + date2);
            Date newDate1 = Util.parseSqlDate(date1);
            Date newDate2 = Util.parseSqlDate(date2);
            query.setDate("date1", newDate1);
            query.setDate("date2", newDate2);
        }
    }
    @SuppressWarnings({ "unused", "unchecked" })
    private List getListForPage(Query query, int offset, int length){
        query.setFirstResult(offset);
        query.setMaxResults(length);
        return query.list();
    }
}
