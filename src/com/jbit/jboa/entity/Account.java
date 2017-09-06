package com.jbit.jboa.entity;

import java.util.Date;

public class Account implements java.io.Serializable {

    private static final long serialVersionUID = -8847282721538564317L;
    private Integer id;
    private String usrId;
    private String accType;
    private Double accMoney;
    private String sheetId;
    private Date accTime;

    /** default constructor */
    public Account() {
    }

    /** full constructor */
    public Account(String usrId, String accType, Double accMoney,
            String sheetId, Date accTime) {
        this.usrId = usrId;
        this.accType = accType;
        this.accMoney = accMoney;
        this.sheetId = sheetId;
        this.accTime = accTime;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsrId() {
        return this.usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }

    public String getAccType() {
        return this.accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public Double getAccMoney() {
        return this.accMoney;
    }

    public void setAccMoney(Double accMoney) {
        this.accMoney = accMoney;
    }

    public String getSheetId() {
        return this.sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }

    public Date getAccTime() {
        return this.accTime;
    }

    public void setAccTime(Date accTime) {
        this.accTime = accTime;
    }

}