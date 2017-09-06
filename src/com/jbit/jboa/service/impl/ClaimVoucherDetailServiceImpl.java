package com.jbit.jboa.service.impl;

import java.util.List;

import com.jbit.jboa.dao.ClaimVoucherDao;
import com.jbit.jboa.dao.ClaimVoucherDetailDao;
import com.jbit.jboa.entity.ClaimVoucherDetail;
import com.jbit.jboa.entity.ClaimVoucher;
import com.jbit.jboa.service.ClaimVoucherDetailService;
/**
 * @author 北大青鸟
 * 报销单详细信息业务类，用于调用dao类相应方法
 * 
 */
public class ClaimVoucherDetailServiceImpl implements ClaimVoucherDetailService {
	ClaimVoucherDao claimVoucherDao;

	ClaimVoucherDetailDao claimVoucherDetailDao;
	
	//添加报销单详细信息
	public ClaimVoucher addClaimVoucherDetail(
			ClaimVoucherDetail claimVoucherDetail) {
		ClaimVoucher claimVoucher = claimVoucherDetail
				.getBizClaimVoucher();
		if (claimVoucher.getId() == 0 || null==claimVoucher.getId() ) {
			if(null==claimVoucher.getTotalAccount() || "".equals(claimVoucher.getTotalAccount())){
				claimVoucher.setTotalAccount(0d);
			}
			claimVoucher=this.claimVoucherDao.saveClaimVoucher(claimVoucher);
			claimVoucherDetail.setBizClaimVoucher(claimVoucher);
			this.claimVoucherDetailDao.saveClaimVoucherDetail(claimVoucherDetail);
			claimVoucher.setTotalAccount(claimVoucherDetail.getAccount());
		}else{
			claimVoucher=this.claimVoucherDao.getClaimVoucher(claimVoucher);
			claimVoucherDetail.setBizClaimVoucher(claimVoucher);
			claimVoucherDetail=this.claimVoucherDetailDao.saveClaimVoucherDetail(claimVoucherDetail);
			claimVoucher.setTotalAccount(claimVoucher.getTotalAccount()+claimVoucherDetail.getAccount());
		}
		claimVoucher=this.claimVoucherDao.updateClaimVoucher(claimVoucher);
        claimVoucher.setBizClaimVoucherDetails(this.getClaimVoucherDetailDao().getDetailsByClaimVoucheId(claimVoucher.getId()));
        return claimVoucher;
	}
	//修改报销单详细信息
	public ClaimVoucher modifyClaimVoucherDetail(ClaimVoucherDetail claimVoucherDetail) {
		ClaimVoucher claimVoucher = claimVoucherDetail.getBizClaimVoucher();
		claimVoucher=this.claimVoucherDao.getClaimVoucher(claimVoucher);
		ClaimVoucherDetail oldClaimVoucherDetail=null;
		double account=0d;
		List<ClaimVoucherDetail> list=this.claimVoucherDetailDao.getDetailsByClaimVoucheDetailId(claimVoucherDetail.getId());
		if(list.size()>0){
			oldClaimVoucherDetail=list.get(0);
			account=oldClaimVoucherDetail.getAccount();
		}
		oldClaimVoucherDetail.setAccount(claimVoucherDetail.getAccount());
		oldClaimVoucherDetail.setBizClaimVoucher(claimVoucherDetail.getBizClaimVoucher());
		oldClaimVoucherDetail.setDesc(claimVoucherDetail.getDesc());
		oldClaimVoucherDetail.setItem(claimVoucherDetail.getItem());
		this.claimVoucherDetailDao.updateClaimVoucherDetail(oldClaimVoucherDetail);
		claimVoucher.setTotalAccount(claimVoucher.getTotalAccount()+(claimVoucherDetail.getAccount()-account));
		claimVoucher=this.claimVoucherDao.updateClaimVoucher(claimVoucher);
		claimVoucher.setBizClaimVoucherDetails(this.getClaimVoucherDetailDao().getDetailsByClaimVoucheId(claimVoucher.getId()));
		return claimVoucher;
	}
	//根据报销单id条件查询报销单信息
	public ClaimVoucherDetail getClaimVoucherDetail(ClaimVoucherDetail claimVoucherDetail,ClaimVoucher claimVoucher) {
		List<ClaimVoucherDetail> list=this.claimVoucherDetailDao.getDetailsByClaimVoucheDetailId(claimVoucherDetail.getId());
		if(list.size()>0){
			claimVoucherDetail=list.get(0);
		}
		claimVoucher=this.claimVoucherDao.getClaimVoucher(claimVoucher);
		claimVoucherDetail.setBizClaimVoucher(claimVoucher);
		return claimVoucherDetail;
	}
	//删除报销单详细信息
	public ClaimVoucher removeClaimVoucherDetail(ClaimVoucherDetail claimVoucherDetail){
		ClaimVoucher claimVoucher = claimVoucherDetail.getBizClaimVoucher();
		List<ClaimVoucherDetail> list=this.claimVoucherDetailDao.getDetailsByClaimVoucheDetailId(claimVoucherDetail.getId());
		double account=0d;
		if(list.size()>0){
			claimVoucherDetail=list.get(0);
			account=claimVoucherDetail.getAccount();
		}
		this.claimVoucherDetailDao.deleteClaimVoucherDetail(claimVoucherDetail);
		claimVoucher=this.claimVoucherDao.getClaimVoucher(claimVoucher);
		claimVoucher.setTotalAccount(claimVoucher.getTotalAccount()-account);
		claimVoucher=this.claimVoucherDao.updateClaimVoucher(claimVoucher);
		claimVoucher.setBizClaimVoucherDetails(this.getClaimVoucherDetailDao().getDetailsByClaimVoucheId(claimVoucher.getId()));
		return claimVoucher;
		
	}
	public ClaimVoucherDao getClaimVoucherDao() {
		return claimVoucherDao;
	}

	public void setClaimVoucherDao(ClaimVoucherDao claimVoucherDao) {
		this.claimVoucherDao = claimVoucherDao;
	}

	public ClaimVoucherDetailDao getClaimVoucherDetailDao() {
		return claimVoucherDetailDao;
	}

	public void setClaimVoucherDetailDao(
			ClaimVoucherDetailDao claimVoucherDetailDao) {
		this.claimVoucherDetailDao = claimVoucherDetailDao;
	}

}
