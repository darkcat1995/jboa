/**
 * 
 */
package com.jbit.jboa.service;

import java.util.List;

import com.jbit.jboa.entity.CheckResult;
import com.jbit.jboa.entity.ClaimVoucher;

/**
 * @author ±±´óÇàÄñ
 * 
 */
public interface CheckResultService {
    public List<CheckResult> querySheetId(Long sheetId);

    public CheckResult addCheckResult(CheckResult checkResult,
            ClaimVoucher claimVoucher);
}
