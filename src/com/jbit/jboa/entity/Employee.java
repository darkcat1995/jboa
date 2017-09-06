package com.jbit.jboa.entity;

import java.util.HashSet;
import java.util.Set;
/**
 * 员工实体类。
 * @author 北大青鸟
 *
 */
@SuppressWarnings("unchecked")
public class Employee implements java.io.Serializable {

    private static final long serialVersionUID = 1617817402850575524L;
    private String sn;
    private Department sysDepartment;
    private Postition sysPosition;
    private String password;
    private String name;
    private String status;
    private Set bizClaimVouchersForNextDealSn = new HashSet(0);
    private Set sysDepartments = new HashSet(0);
    private Set bizClaimVouchersForCreateSn = new HashSet(0);
    private Set bizCheckResults = new HashSet(0);

    /** default constructor */
    public Employee() {
    }

    /** minimal constructor */
    public Employee(Department sysDepartment,
            Postition sysPosition, String password, String name,
            String status) {
        this.sysDepartment = sysDepartment;
        this.sysPosition = sysPosition;
        this.password = password;
        this.name = name;
        this.status = status;
    }

    /** full constructor */
    public Employee(Department sysDepartment,
            Postition sysPosition, String password, String name,
            String status, Set bizClaimVouchersForNextDealSn,
            Set sysDepartments, Set bizClaimVouchersForCreateSn,
            Set bizCheckResults) {
        this.sysDepartment = sysDepartment;
        this.sysPosition = sysPosition;
        this.password = password;
        this.name = name;
        this.status = status;
        this.bizClaimVouchersForNextDealSn = bizClaimVouchersForNextDealSn;
        this.sysDepartments = sysDepartments;
        this.bizClaimVouchersForCreateSn = bizClaimVouchersForCreateSn;
        this.bizCheckResults = bizCheckResults;
    }

    public String getSn() {
        return this.sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Department getSysDepartment() {
        return this.sysDepartment;
    }

    public void setSysDepartment(Department sysDepartment) {
        this.sysDepartment = sysDepartment;
    }

    public Postition getSysPosition() {
        return this.sysPosition;
    }

    public void setSysPosition(Postition sysPosition) {
        this.sysPosition = sysPosition;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set getBizClaimVouchersForNextDealSn() {
        return this.bizClaimVouchersForNextDealSn;
    }

    public void setBizClaimVouchersForNextDealSn(
            Set bizClaimVouchersForNextDealSn) {
        this.bizClaimVouchersForNextDealSn = bizClaimVouchersForNextDealSn;
    }

    public Set getSysDepartments() {
        return this.sysDepartments;
    }

    public void setSysDepartments(Set sysDepartments) {
        this.sysDepartments = sysDepartments;
    }

    public Set getBizClaimVouchersForCreateSn() {
        return this.bizClaimVouchersForCreateSn;
    }

    public void setBizClaimVouchersForCreateSn(Set bizClaimVouchersForCreateSn) {
        this.bizClaimVouchersForCreateSn = bizClaimVouchersForCreateSn;
    }

    public Set getBizCheckResults() {
        return this.bizCheckResults;
    }

    public void setBizCheckResults(Set bizCheckResults) {
        this.bizCheckResults = bizCheckResults;
    }

}