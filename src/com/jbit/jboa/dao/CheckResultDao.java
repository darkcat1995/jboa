/**
 * 
 */
package com.jbit.jboa.dao;

import java.util.List;

import com.jbit.jboa.entity.CheckResult;

/**
 * @author ��������
 * 
 */
public interface CheckResultDao {
	public List<CheckResult> getCheckResult(Long sheetId);

	public void insertCheckResult(CheckResult checkResult);
}
