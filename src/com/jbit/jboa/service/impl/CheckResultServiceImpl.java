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
 * @author �������� 
 * ���������ҵ���࣬���ڵ���DAO����Ӧ����
 * 
 */
public class CheckResultServiceImpl implements CheckResultService {
    private CheckResultDao checkResultDao;

    private ClaimVoucherDao claimVoucherDao;

    /*
     * ���ݱ�����id��ѯ�ñ����������Ϣ
     * 
     * @see com.jbit.jboa.service.CheckResultService#querySheetId()
     */
    public List<CheckResult> querySheetId(Long sheetId) {
        return this.checkResultDao.getCheckResult(sheetId);
    }

    // ��ӱ�������˼�¼
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
