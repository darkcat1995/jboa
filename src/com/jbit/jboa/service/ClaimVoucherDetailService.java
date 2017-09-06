package com.jbit.jboa.service;

import com.jbit.jboa.entity.ClaimVoucherDetail;
import com.jbit.jboa.entity.ClaimVoucher;

public interface ClaimVoucherDetailService {
    public ClaimVoucher addClaimVoucherDetail(
            ClaimVoucherDetail claimVoucherDetail);

    public ClaimVoucherDetail getClaimVoucherDetail(
            ClaimVoucherDetail claimVoucherDetail,
            ClaimVoucher claimVoucher);

    public ClaimVoucher modifyClaimVoucherDetail(
            ClaimVoucherDetail claimVoucherDetail);

    public ClaimVoucher removeClaimVoucherDetail(
            ClaimVoucherDetail claimVoucherDetail);

}
