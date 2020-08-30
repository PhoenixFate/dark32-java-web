package com.phoenix.domain;

import java.util.HashSet;
import java.util.Set;

public class Customer {
	/*
	 * CREATE TABLE `cst_customer` (
	  `cust_id` BIGINT(32) NOT NULL AUTO_INCREMENT COMMENT '客户编号(主键)',
	  `cust_name` VARCHAR(32) NOT NULL COMMENT '客户名称(公司名称)',
	  `cust_source` VARCHAR(32) DEFAULT NULL COMMENT '客户信息来源',
	  `cust_industry` VARCHAR(32) DEFAULT NULL COMMENT '客户所属行业',
	  `cust_level` VARCHAR(32) DEFAULT NULL COMMENT '客户级别',
	  `cust_linkman` VARCHAR(64) DEFAULT NULL COMMENT '联系人',
	  `cust_phone` VARCHAR(64) DEFAULT NULL COMMENT '固定电话',
	  `cust_mobile` VARCHAR(16) DEFAULT NULL COMMENT '移动电话',
	  PRIMARY KEY (`cust_id`)
	) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
	 */
	private Long customerId;
	private String customerName;
	private String customerSource;
	private String customerIndustry;
	private String customerLevel;
	private String customerLinkman;
	private String customerPhone;
	private String customerMobile;

	//表达1对多的关系
	private Set<Linkman> linkmanSet=new HashSet<Linkman>();


	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerSource() {
		return customerSource;
	}

	public void setCustomerSource(String customerSource) {
		this.customerSource = customerSource;
	}

	public String getCustomerIndustry() {
		return customerIndustry;
	}

	public void setCustomerIndustry(String customerIndustry) {
		this.customerIndustry = customerIndustry;
	}

	public String getCustomerLevel() {
		return customerLevel;
	}

	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}

	public String getCustomerLinkman() {
		return customerLinkman;
	}

	public void setCustomerLinkman(String customerLinkman) {
		this.customerLinkman = customerLinkman;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public Set<Linkman> getLinkmanSet() {
		return linkmanSet;
	}

	public void setLinkmanSet(Set<Linkman> linkmanSet) {
		this.linkmanSet = linkmanSet;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"customerId=" + customerId +
				", customerName='" + customerName + '\'' +
				", customerSource='" + customerSource + '\'' +
				", customerIndustry='" + customerIndustry + '\'' +
				", customerLevel='" + customerLevel + '\'' +
				", customerLinkman='" + customerLinkman + '\'' +
				", customerPhone='" + customerPhone + '\'' +
				", customerMobile='" + customerMobile + '\'' +
				", linkmanSet=" + linkmanSet +
				'}';
	}
}
