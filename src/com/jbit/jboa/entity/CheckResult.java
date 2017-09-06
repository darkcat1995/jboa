package com.jbit.jboa.entity;

import java.util.Date;

/**
 * 审核结果。
 * @author 北大青鸟
 *
 */
public class CheckResult implements java.io.Serializable {

    private static final long serialVersionUID = -6927459782166236900L;
    private Long id;
    private Employee sysEmployee;
    private String sheetType;
    private Long sheetId;
    private Date checkTime;
    private String type;
    private String result;
    private String comment;

    /** default constructor */
    public CheckResult() {
    }

    /** minimal constructor */
    public CheckResult(Employee sysEmployee, String sheetType,
            Long sheetId, Date checkTime, String type, String result) {
        this.sysEmployee = sysEmployee;
        this.sheetType = sheetType;
        this.sheetId = sheetId;
        this.checkTime = checkTime;
        this.type = type;
        this.result = result;
    }

    /** full constructor */
    public CheckResult(Employee sysEmployee, String sheetType,
            Long sheetId, Date checkTime, String type, String result,
            String comment) {
        this.sysEmployee = sysEmployee;
        this.sheetType = sheetType;
        this.sheetId = sheetId;
        this.checkTime = checkTime;
        this.type = type;
        this.result = result;
        this.comment = comment;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getSysEmployee() {
        return this.sysEmployee;
    }

    public void setSysEmployee(Employee sysEmployee) {
        this.sysEmployee = sysEmployee;
    }

    public String getSheetType() {
        return this.sheetType;
    }

    public void setSheetType(String sheetType) {
        this.sheetType = sheetType;
    }

    public Long getSheetId() {
        return this.sheetId;
    }

    public void setSheetId(Long sheetId) {
        this.sheetId = sheetId;
    }

    public Date getCheckTime() {
        return this.checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}