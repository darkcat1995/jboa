package com.jbit.jboa.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jbit.jboa.dao.CheckResultDao;
import com.jbit.jboa.entity.CheckResult;

/**
 * @author 北大青鸟 
 * 该类主要用于处理报销单审核操作
 * 
 */
public class CheckResultDaoImpl extends HibernateDaoSupport implements
        CheckResultDao {
    // 根据报销单id得到审核结果
    @SuppressWarnings("unchecked")
    public List<CheckResult> getCheckResult(Long sheetId) {
        return super.getHibernateTemplate().find(
                "from CheckResult c where c.sheetId=" + sheetId);
    }

    // 向苦中添加审核结果
    public void insertCheckResult(CheckResult checkResult) {
        super.getHibernateTemplate().save(checkResult);
    }

}
