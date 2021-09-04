package com.oauth2example.endpoint.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "TBL_CUSTOMER")
@Entity
public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CUSTOMER_ID")
	private Long customerId;
	
	@Column(name = "CUSTOMER_NAME")
	private String customerName;
	
	@Column(name = "CUSTOMER_CREDIT_CARD")
	private String customerCreditCard;
	
	@Column(name = "CUSTOMER_PERSONAL_ID")
	private String customerPersonalId;

}
