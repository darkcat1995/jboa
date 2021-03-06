package com.jbit.jboa.entity;

/**
 * 报销单明细实体类。
 * @author 北大青鸟
 *
 */
public class ClaimVoucherDetail implements java.io.Serializable {

    private static final long serialVersionUID = 8187404228626478972L;
    private Long id;
    private ClaimVoucher bizClaimVoucher;
    private String item;
    private Double account;
    private String desc;

    /** default constructor */
    public ClaimVoucherDetail() {
    }

    /** full constructor */
    public ClaimVoucherDetail(ClaimVoucher bizClaimVoucher,
            String item, Double account, String desc) {
        this.bizClaimVoucher = bizClaimVoucher;
        this.item = item;
        this.account = account;
        this.desc = desc;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClaimVoucher getBizClaimVoucher() {
        return this.bizClaimVoucher;
    }

    public void setBizClaimVoucher(ClaimVoucher bizClaimVoucher) {
        this.bizClaimVoucher = bizClaimVoucher;
    }

    public String getItem() {
        return this.item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getAccount() {
        return this.account;
    }

    public void setAccount(Double account) {
        this.account = account;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}