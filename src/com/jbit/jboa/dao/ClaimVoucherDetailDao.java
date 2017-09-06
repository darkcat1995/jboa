package com.jbit.jboa.dao;

import java.io.Serializable;
import java.util.List;

import com.jbit.jboa.entity.ClaimVoucherDetail;

public interface ClaimVoucherDetailDao {
    public ClaimVoucherDetail saveClaimVoucherDetail(
            ClaimVoucherDetail claimVoucherDetailEntity);

    List<ClaimVoucherDetail> getDetailsByClaimVoucheId(Serializable id);

    public List<ClaimVoucherDetail> getDetailsByClaimVoucheDetailId(
            Serializable id);

    public void updateClaimVoucherDetail(
            ClaimVoucherDetail claimVoucherDetail);

    public void deleteClaimVoucherDetail(
            ClaimVoucherDetail claimVoucherDetail);
}
