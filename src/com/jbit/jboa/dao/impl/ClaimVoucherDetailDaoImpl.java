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
 * @author ��������
 * �������ڴ���������ϸ��Ϣ����ɾ�Ĳ����
 */
public class ClaimVoucherDetailDaoImpl extends HibernateDaoSupport implements
        ClaimVoucherDetailDao {
    // ���ӱ�������ϸ��Ϣ
    public ClaimVoucherDetail saveClaimVoucherDetail(
            ClaimVoucherDetail claimVoucherDetail) {
        super.getHibernateTemplate().save(claimVoucherDetail);
        return claimVoucherDetail;

    }

    // ���ݱ�����id�õ����иñ���������ϸ��Ϣ
    @SuppressWarnings("unchecked")
    public List<ClaimVoucherDetail> getDetailsByClaimVoucheId(
            Serializable id) {
        List<ClaimVoucherDetail> list = super.getHibernateTemplate()
                .find("from ClaimVoucherDetail cvd where cvd.bizClaimVoucher.id="
                                + id);
        return list;
    }

    // ���ݱ�������ϸ��Ϣid�õ���������ϸ��Ϣ
    @SuppressWarnings("unchecked")
    public List<ClaimVoucherDetail> getDetailsByClaimVoucheDetailId(
            Serializable id) {
        List<ClaimVoucherDetail> list = super.getHibernateTemplate()
                .find("from ClaimVoucherDetail cvd where cvd.id=" + id);
        return list;
    }

    // ���±�������ϸ��Ϣ
    public void updateClaimVoucherDetail(
            ClaimVoucherDetail claimVoucherDetail) {
        this.getHibernateTemplate().update(claimVoucherDetail);
    }

    // ɾ����������ϸ��Ϣ
    public void deleteClaimVoucherDetail(
            ClaimVoucherDetail claimVoucherDetail) {
        this.getHibernateTemplate().delete(claimVoucherDetail);
    }

}
