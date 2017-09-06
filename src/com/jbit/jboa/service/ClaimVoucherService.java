/**
 * 
 */
package com.jbit.jboa.service;

import com.jbit.jboa.entity.ClaimVoucherDetail;
import com.jbit.jboa.entity.ClaimVoucher;
import com.jbit.jboa.entity.Employee;
import com.jbit.jboa.util.PageBean;

/**
 * @author ±±´óÇàÄñ
 * 
 */
public interface ClaimVoucherService {
    public ClaimVoucher modifyClaimVoucher(
            ClaimVoucher claimVoucher,
            ClaimVoucherDetail claimVoucherDetail, String type);

    public PageBean queryForPage(int pageSize, int page,
            ClaimVoucher condition, Employee employee);

    public void removeClaimVoucher(ClaimVoucher claimVoucher);

    public ClaimVoucher query(ClaimVoucher claimVoucher);

    public PageBean queryClaimVoucher(int pageSize, int page,
            ClaimVoucher condition, Employee employee);

    public PageBean queryByDate(int pageSize, int page,
            ClaimVoucher condition, Employee employee);
}
