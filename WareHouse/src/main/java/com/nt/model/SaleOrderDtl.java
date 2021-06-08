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
@Table(name="sale_dt1_tab")
public class SaleOrderDtl {
	
	@Id
	@GeneratedValue(generator = "sdt1_gen")
	@SequenceGenerator(name="sdt1_gen",sequenceName = "pdtl_seq")
	private Integer id;
	
	@Column(name="so_dlt_qty_col")
	private Integer qty;
	
	// module intigration 
	@ManyToOne
	@JoinColumn(name="part_id_fk_col")
	private Part part;
	
	@ManyToOne
	@JoinColumn(name="so_id_fk_col")
	private SaleOrder so;
	

}
