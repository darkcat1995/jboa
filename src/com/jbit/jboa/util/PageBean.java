package com.jbit.jboa.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@SuppressWarnings("unchecked")
public class PageBean {

    private List list;// Ҫ���ص�ĳһҳ�ļ�¼�б�
    private int allRow; // �ܼ�¼��
    private int totalPage;// ��ҳ��
    private int currentPage;// ��ǰҳ
    private int pageSize;// ÿҳ��¼��
    private int totalGroup;// ҳ���ܹ��ּ���
    private int currentGroup;// ��ǰ��    
	@SuppressWarnings("unused")
	private List pageList;// ��ŷ�ҳ��5ҳ��Ϣ
    @SuppressWarnings("unused")
	private boolean isFirstPage;// �Ƿ�Ϊ��һҳ
    @SuppressWarnings("unused")
	private boolean isLastPage;// �Ƿ�Ϊ���һҳ
    @SuppressWarnings("unused")
	private boolean hasPreviousPage;// �Ƿ���ǰһҳ
    @SuppressWarnings("unused")
	private boolean hasNextPage;// �Ƿ�����һҳ

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getAllRow() {
        return allRow;
    }

    public void setAllRow(int allRow) {
        this.allRow = allRow;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * ��ʼ����ҳ��Ϣ
     */
    public void init() {
        this.isFirstPage = isFirstPage();
        this.isLastPage = isLastPage();
        this.hasPreviousPage = isHasPreviousPage();
        this.hasNextPage = isHasNextPage();
    }

    /**
     * �����ж�ҳ����Ϣ,ֻ��getter����(is����)����
     */

    public boolean isFirstPage() {
        return currentPage == 1;// ���ǵ�ǰҳ�ǵ�1ҳ
    }

    public boolean isLastPage() {
        return currentPage == totalPage;// �����ǰҳ�����һҳ
    }

    public boolean isHasPreviousPage() {
        return currentPage != 1;// ֻҪ��ǰҳ���ǵ�1ҳ
    }

    public boolean isHasNextPage() {
        return currentPage != totalPage;// ֻҪ��ǰҳ�������1ҳ
    }

    /** */
    /**
     * ������ҳ��,��̬����,���ⲿֱ��ͨ����������
     * 
     * @param pageSize
     *            ÿҳ��¼��
     * @param allRow
     *            �ܼ�¼��
     * @return ��ҳ��
     */
    public static int countTotalPage(final int pageSize, final int allRow) {
        int totalPage = allRow % pageSize == 0 ? allRow / pageSize : allRow
                / pageSize + 1;
        return totalPage;
    }

    /** */
    /**
     * ���㵱ǰҳ��ʼ��¼
     * 
     * @param pageSize
     *            ÿҳ��¼��
     * @param currentPage
     *            ��ǰ�ڼ�ҳ
     * @return ��ǰҳ��ʼ��¼��
     */
    public static int countOffset(final int pageSize, final int currentPage) {
        final int offset = pageSize * (currentPage - 1);
        return offset;
    }

    /**
     * ���㵱ǰҳ,��Ϊ0���������URL��û��"?page=",����1����
     * 
     * @param page ����Ĳ���(����Ϊ��,��0,�򷵻�1)
     * @return ��ǰҳ
     */
    public static int countCurrentPage(int page) {
        final int curPage = (page == 0 ? 1 : page);
        return curPage;
    }

    /**
     * ��������ҳ����Ϊ����ֵ
     * @return ��ǰ��Ҫ��ʾ���а�����ҳ��Ϣ
     */
    public List getPageList() {
        int t = (currentGroup == 0 ? 1 : currentGroup);
        int count = t * 5;
        List pageList1 = new ArrayList();
        for (int i = count; i > count - 5; i--) {
            Integer index = new Integer(0);
            index = i;
            if (i <= totalPage) {
                pageList1.add(index.toString());
            }
        }
        Collections.sort(pageList1);
        return pageList1;
    }

    public static void main(String[] args) {
        PageBean p = new PageBean();
        p.currentGroup = 3;
        List list = p.getPageList();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public int getCurrentGroup() {
        final int curGroup = (currentGroup == 0 ? 1 : currentGroup);
        return curGroup;
    }

    public void setCurrentGroup(int currentGroup) {
        final int curGroup = (currentGroup == 0 ? 1 : currentGroup);
        this.currentGroup = curGroup;
    }

    public static int countTotalGroup(int totalPage) {
        int totalGroup = totalPage % 5 == 0 ? totalPage / 5 : totalPage / 5 + 1;
        return totalGroup;
    }

    public int getTotalGroup() {
        return totalGroup;
    }

    public void setTotalGroup(int totalGroup) {
        this.totalGroup = totalGroup;
    }
}
