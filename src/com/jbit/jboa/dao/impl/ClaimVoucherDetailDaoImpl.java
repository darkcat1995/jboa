/**
 * 
 */
package com.jbit.jboa.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jbit.jboa.dao.ClaimVoucherDetailDao;
import com.jbit.jboa.entity.ClaimVoucherDetail;

/**
 * @author 北大青鸟
 * 该类用于处理报销单详细信息的增删改查操作
 */
public class ClaimVoucherDetailDaoImpl extends HibernateDaoSupport implements
        ClaimVoucherDetailDao {
    // 增加报销单详细信息
    public ClaimVoucherDetail saveClaimVoucherDetail(
            ClaimVoucherDetail claimVoucherDetail) {
        super.getHibernateTemplate().save(claimVoucherDetail);
        return claimVoucherDetail;

    }

    // 根据报销单id得到所有该报销单的详细信息
    @SuppressWarnings("unchecked")
    public List<ClaimVoucherDetail> getDetailsByClaimVoucheId(
            Serializable id) {
        List<ClaimVoucherDetail> list = super.getHibernateTemplate()
                .find("from ClaimVoucherDetail cvd where cvd.bizClaimVoucher.id="
                                + id);
        return list;
    }

    // 根据报销单详细信息id得到报销单详细信息
    @SuppressWarnings("unchecked")
    public List<ClaimVoucherDetail> getDetailsByClaimVoucheDetailId(
            Serializable id) {
        List<ClaimVoucherDetail> list = super.getHibernateTemplate()
                .find("from ClaimVoucherDetail cvd where cvd.id=" + id);
        return list;
    }

    // 更新报销单详细信息
    public void updateClaimVoucherDetail(
            ClaimVoucherDetail claimVoucherDetail) {
        this.getHibernateTemplate().update(claimVoucherDetail);
    }

    // 删除报销单详细信息
    public void deleteClaimVoucherDetail(
            ClaimVoucherDetail claimVoucherDetail) {
        this.getHibernateTemplate().delete(claimVoucherDetail);
    }

}
