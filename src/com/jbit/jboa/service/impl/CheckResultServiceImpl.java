/**
 * 
 */
package com.jbit.jboa.service.impl;

import java.util.List;

import com.jbit.jboa.dao.CheckResultDao;
import com.jbit.jboa.dao.ClaimVoucherDao;
import com.jbit.jboa.entity.CheckResult;
import com.jbit.jboa.entity.ClaimVoucher;
import com.jbit.jboa.service.CheckResultService;

/**
 * @author 北大青鸟 
 * 报销单审核业务类，用于调用DAO类相应方法
 * 
 */
public class CheckResultServiceImpl implements CheckResultService {
    private CheckResultDao checkResultDao;

    private ClaimVoucherDao claimVoucherDao;

    /*
     * 根据报销单id查询该报销单审核信息
     * 
     * @see com.jbit.jboa.service.CheckResultService#querySheetId()
     */
    public List<CheckResult> querySheetId(Long sheetId) {
        return this.checkResultDao.getCheckResult(sheetId);
    }

    // 添加报销单审核记录
    public CheckResult addCheckResult(CheckResult checkResult,
            ClaimVoucher claimVoucher) {
        checkResultDao.insertCheckResult(checkResult);
        claimVoucher = this.claimVoucherDao.updateClaimVoucher(claimVoucher);
        return null;
    }

    public CheckResultDao getCheckResultDao() {
        return checkResultDao;
    }

    public void setCheckResultDao(CheckResultDao checkResultDao) {
        this.checkResultDao = checkResultDao;
    }

    public ClaimVoucherDao getClaimVoucherDao() {
        return claimVoucherDao;
    }

    public void setClaimVoucherDao(ClaimVoucherDao claimVoucherDao) {
        this.claimVoucherDao = claimVoucherDao;
    }

}
