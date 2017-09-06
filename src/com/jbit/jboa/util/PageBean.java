package com.jbit.jboa.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@SuppressWarnings("unchecked")
public class PageBean {

    private List list;// 要返回的某一页的记录列表
    private int allRow; // 总记录数
    private int totalPage;// 总页数
    private int currentPage;// 当前页
    private int pageSize;// 每页记录数
    private int totalGroup;// 页数总共分几组
    private int currentGroup;// 当前组    
	@SuppressWarnings("unused")
	private List pageList;// 存放分页中5页信息
    @SuppressWarnings("unused")
	private boolean isFirstPage;// 是否为第一页
    @SuppressWarnings("unused")
	private boolean isLastPage;// 是否为最后一页
    @SuppressWarnings("unused")
	private boolean hasPreviousPage;// 是否有前一页
    @SuppressWarnings("unused")
	private boolean hasNextPage;// 是否有下一页

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
     * 初始化分页信息
     */
    public void init() {
        this.isFirstPage = isFirstPage();
        this.isLastPage = isLastPage();
        this.hasPreviousPage = isHasPreviousPage();
        this.hasNextPage = isHasNextPage();
    }

    /**
     * 以下判断页的信息,只需getter方法(is方法)即可
     */

    public boolean isFirstPage() {
        return currentPage == 1;// 如是当前页是第1页
    }

    public boolean isLastPage() {
        return currentPage == totalPage;// 如果当前页是最后一页
    }

    public boolean isHasPreviousPage() {
        return currentPage != 1;// 只要当前页不是第1页
    }

    public boolean isHasNextPage() {
        return currentPage != totalPage;// 只要当前页不是最后1页
    }

    /** */
    /**
     * 计算总页数,静态方法,供外部直接通过类名调用
     * 
     * @param pageSize
     *            每页记录数
     * @param allRow
     *            总记录数
     * @return 总页数
     */
    public static int countTotalPage(final int pageSize, final int allRow) {
        int totalPage = allRow % pageSize == 0 ? allRow / pageSize : allRow
                / pageSize + 1;
        return totalPage;
    }

    /** */
    /**
     * 计算当前页开始记录
     * 
     * @param pageSize
     *            每页记录数
     * @param currentPage
     *            当前第几页
     * @return 当前页开始记录号
     */
    public static int countOffset(final int pageSize, final int currentPage) {
        final int offset = pageSize * (currentPage - 1);
        return offset;
    }

    /**
     * 计算当前页,若为0或者请求的URL中没有"?page=",则用1代替
     * 
     * @param page 传入的参数(可能为空,即0,则返回1)
     * @return 当前页
     */
    public static int countCurrentPage(int page) {
        final int curPage = (page == 0 ? 1 : page);
        return curPage;
    }

    /**
     * 计算所有页共分为几组值
     * @return 当前所要显示组中包含的页信息
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
