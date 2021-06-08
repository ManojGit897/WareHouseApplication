package com.nt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="po_tab")
public class PurchaseOrder {

	@Id
	@GeneratedValue(generator = "po_gen")
	@SequenceGenerator(name = "po_gen",sequenceName = "po_seq")
	@Column(name="po_id_col")
	private Integer id;
	
	@Column(name="po_code_col")
	private String orderCode;
	
	@Column(name="po_ref_col")
	private String refNum;
	
	@Column(name="po_qlty_col")
	private String qltyChck;
	
	@Column(name="po_status_col")
	private String status;

	@Column(name="po_desc_col")
	private String description;
	
	
	//Integrations
	//ShipmentType
	@ManyToOne
	@JoinColumn(name="st_id_fk_col")
	private ShipmentType st;
	
	//WhUserType
	@ManyToOne
	@JoinColumn(name="wh_ven_id_fk_col")
	private WhUserType vendor;
	
}
