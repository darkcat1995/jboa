package com.jbit.jboa.entity;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.jbit.jboa.util.Util;

/**
 * 报销单实体类。
 * @author 北大青鸟
 *
 */
@SuppressWarnings("unchecked")
public class ClaimVoucher implements java.io.Serializable {

	private static final long serialVersionUID = -8423258065596709084L;
	private Long id;
	private Employee creator;
	private Employee nextDealBy;
	private Date createTime;
	private String event;
	private Double totalAccount = 0d;
	private String status;
	private List bizClaimVoucherDetails = new ArrayList();

	// Constructors

	/** default constructor */
	public ClaimVoucher() {
	}

	/** minimal constructor */
	public ClaimVoucher(Employee creator, Date createTime,
			String event, Double totalAccount, String status) {
		this.creator = creator;
		this.createTime = createTime;
		this.event = event;
		this.totalAccount = totalAccount;
		this.status = status;
	}

	/** full constructor */
	public ClaimVoucher(Employee creator,
			Employee nextDealBy, Date createTime, String event,
			Double totalAccount, String status, List bizClaimVoucherDetails) {
		this.creator = creator;
		this.nextDealBy = nextDealBy;
		this.createTime = createTime;
		this.event = event;
		this.totalAccount = totalAccount;
		this.status = status;
		this.bizClaimVoucherDetails = bizClaimVoucherDetails;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getCreator() {
		return creator;
	}

	public void setCreator(Employee creator) {
		this.creator = creator;
	}

	public Employee getNextDealBy() {
		return nextDealBy;
	}

	public void setNextDealBy(Employee nextDealBy) {
		this.nextDealBy = nextDealBy;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setCreateTime(String createTime) {

		this.createTime = Util.parseSqlDate(createTime);
	}

	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Double getTotalAccount() {
		return this.totalAccount;
	}

	public void setTotalAccount(Double totalAccount) {
		this.totalAccount = totalAccount;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List getBizClaimVoucherDetails() {
		return bizClaimVoucherDetails;
	}

	public void setBizClaimVoucherDetails(List bizClaimVoucherDetails) {
		this.bizClaimVoucherDetails = bizClaimVoucherDetails;
	}

}