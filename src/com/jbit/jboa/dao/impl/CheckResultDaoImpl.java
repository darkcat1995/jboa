package com.jbit.jboa.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jbit.jboa.dao.CheckResultDao;
import com.jbit.jboa.entity.CheckResult;

/**
 * @author �������� 
 * ������Ҫ���ڴ���������˲���
 * 
 */
public class CheckResultDaoImpl extends HibernateDaoSupport implements
        CheckResultDao {
    // ���ݱ�����id�õ���˽��
    @SuppressWarnings("unchecked")
    public List<CheckResult> getCheckResult(Long sheetId) {
        return super.getHibernateTemplate().find(
                "from CheckResult c where c.sheetId=" + sheetId);
    }

    // ����������˽��
    public void insertCheckResult(CheckResult checkResult) {
        super.getHibernateTemplate().save(checkResult);
    }

}
