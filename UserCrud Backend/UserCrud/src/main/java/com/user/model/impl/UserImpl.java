package com.user.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.user.model.IEntity;

@Entity
@Table(name = "User")
public class UserImpl implements IEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long userId;
	
	@Column(name = "TC_KIMLIK_NO")
	private Long tckn;
	
	@Column(name = "EMAIL")
	private String eMail;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "GSM")
	private String gsm;
	
	@Column(name = "ADDRESS")
	@Lob
	private String address;

	public UserImpl() {
		super();
	}

	public UserImpl(Long userId, Long tckn, String eMail, String password, String gsm, String address) {
		super();
		this.userId = userId;
		this.tckn = tckn;
		this.eMail = eMail;
		this.password = password;
		this.gsm = gsm;
		this.address = address;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTckn() {
		return tckn;
	}

	public void setTckn(Long tckn) {
		this.tckn = tckn;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGsm() {
		return gsm;
	}

	public void setGsm(String gsm) {
		this.gsm = gsm;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", tckn=" + tckn + ", eMail=" + eMail + ", password=" + password + ", gsm="
				+ gsm + ", address=" + address + "]";
	}
}
