/**
 * 
 */
package com.jbit.jboa.dao;

import java.util.List;

import com.jbit.jboa.entity.ClaimVoucher;
import com.jbit.jboa.entity.Employee;

/**
 * @author ±±´óÇàÄñ
 * 
 */
public interface ClaimVoucherDao {
    public List<ClaimVoucher> find(ClaimVoucher condition);

    public List<ClaimVoucher> findAll();

    public ClaimVoucher saveClaimVoucher(ClaimVoucher claimVoucher);

    public ClaimVoucher updateClaimVoucher(ClaimVoucher claimVoucher);

    public int getAllRowCount(final String hql,
            final ClaimVoucher condition, final Employee employee,
            String type);

    public int getAllRowCountM(final String hql,
            final ClaimVoucher condition, final Employee employee,
            String type);

    public List<ClaimVoucher> queryForPage(final String hql,
            final int offset, final int length,
            final ClaimVoucher condition, Employee employee,
            String type);

    public void deletClaimVoucher(ClaimVoucher claimVoucher);

    public ClaimVoucher getClaimVoucher(ClaimVoucher claimVoucher);

    public List<ClaimVoucher> queryForPageM(final String hql,
            final int offset, final int length,
            final ClaimVoucher condition, final Employee employee,
            String type);
}
