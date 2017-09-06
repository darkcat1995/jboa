package com.jbit.jboa.action;

import java.io.ByteArrayInputStream;

import com.jbit.jboa.util.RandomNumUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author �������� 
 * ������Ҫ����������֤��
 * 
 */
public class RandomAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private ByteArrayInputStream inputStream;

    // ������֤�뷽��
    public String execute() throws Exception {
        RandomNumUtil rdnu = RandomNumUtil.Instance();
        this.setInputStream(rdnu.getImage());// ȡ�ô�������ַ�����ͼƬ
        ActionContext.getContext().getSession().put("random", rdnu.getString());// ȡ������ַ�������HttpSession
        return SUCCESS;
    }

    public void setInputStream(ByteArrayInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ByteArrayInputStream getInputStream() {
        return inputStream;
    }
}
