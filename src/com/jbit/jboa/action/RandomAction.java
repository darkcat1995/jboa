package com.jbit.jboa.action;

import java.io.ByteArrayInputStream;

import com.jbit.jboa.util.RandomNumUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author 北大青鸟 
 * 该类主要用于生成验证码
 * 
 */
public class RandomAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private ByteArrayInputStream inputStream;

    // 生成验证码方法
    public String execute() throws Exception {
        RandomNumUtil rdnu = RandomNumUtil.Instance();
        this.setInputStream(rdnu.getImage());// 取得带有随机字符串的图片
        ActionContext.getContext().getSession().put("random", rdnu.getString());// 取得随机字符串放入HttpSession
        return SUCCESS;
    }

    public void setInputStream(ByteArrayInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ByteArrayInputStream getInputStream() {
        return inputStream;
    }
}
