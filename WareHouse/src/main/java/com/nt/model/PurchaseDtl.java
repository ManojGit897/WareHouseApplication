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

@Entity
@Data
@Table(name="po_dt1_tab")
public class PurchaseDtl {
	
	@Id
	@GeneratedValue(generator = "pdt1_gen")
	@SequenceGenerator(name="pdt1_gen",sequenceName = "pdtl_seq")
	@Column(name="po_dlt_id_col")
	private Integer id;
	
	@Column(name="po_dlt_qty_col")
	private Integer qty;
	
	// module intigration 
	@ManyToOne
	@JoinColumn(name="part_id_fk_col")
	private Part part;
	
	@ManyToOne
	@JoinColumn(name="po_id_fk_col")
	private PurchaseOrder po;
	

}
