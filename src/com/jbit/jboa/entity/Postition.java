package com.jbit.jboa.entity;

import java.util.HashSet;
import java.util.Set;
/**
 * 职位实体类。
 * @author 北大青鸟
 *
 */
@SuppressWarnings("unchecked")
public class Postition implements java.io.Serializable {

    private static final long serialVersionUID = -1777501621600424586L;
    private Integer id;
    private String nameCn;
    private String nameEn;
    private Set sysEmployees = new HashSet(0);

    /** default constructor */
    public Postition() {
    }

    /** minimal constructor */
    public Postition(String nameCn, String nameEn) {
        this.nameCn = nameCn;
        this.nameEn = nameEn;
    }

    /** full constructor */
    public Postition(String nameCn, String nameEn, Set sysEmployees) {
        this.nameCn = nameCn;
        this.nameEn = nameEn;
        this.sysEmployees = sysEmployees;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameCn() {
        return this.nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameEn() {
        return this.nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Set getSysEmployees() {
        return this.sysEmployees;
    }

    public void setSysEmployees(Set sysEmployees) {
        this.sysEmployees = sysEmployees;
    }

}