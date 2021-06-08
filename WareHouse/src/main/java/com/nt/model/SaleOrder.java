package com.nt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity
public class SaleOrder {
	@Id
	@GeneratedValue(generator = "so_gen")
	@SequenceGenerator(name = "so_gen",sequenceName = "so_seq")
	@Column(name="so_id_col")
	private Integer id;
	
	@Column(name="so_order_col")
	private String orderCode;
	
	@Column(name="so_ref_col")
	private String refNum;
	
	@Column(name="so_sm_col")
	private String stockMode;
	
	@Column(name="so_sc_col")
	private String stockSource;
	
	@Column(name="so_status_col")
	private String status;
	
	@Column(name="so_desc_col")
	private String description;
	
	//Integrations
	//ShipmentType
	@ManyToOne
	@JoinColumn(name="st_id_fk_col")
	private ShipmentType st;
	
	//WhUserType
	@ManyToOne
	@JoinColumn(name="wh_cust_id_fk_col")
	private WhUserType customer;
	
	
}