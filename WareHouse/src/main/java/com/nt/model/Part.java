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
@Table(name="part_tab")
public class Part {

	@Id
	@GeneratedValue(generator = "part_gen")
	@SequenceGenerator(name = "part_gen" , sequenceName = "part_seq")
	@Column(name="part_id_col")
	private Integer id;
	
	@Column(name="part_code_col",
			nullable = false,
			length = 20,
			unique = true)
	private String partCode;

	@Column(name="part_curr_col",
			nullable = false,
			length = 20)
	private String partCurrency;

	@Column(name="part_cost_col",
			nullable = false,
			length = 20)
	private Double partBaseCost;
	
	@Column(name="part_wid_col",
			nullable = false,
			length = 20)
	private Double partWid;
	
	@Column(name="part_ht_col",
			nullable = false,
			length = 20)
	private Double partHt;
	
	@Column(name="part_len_col",
			nullable = false,
			length = 20)
	private Double partLen;
	
	@Column(name="part_desc_col",
			nullable = false,
			length = 150)
	private String partDesc;
	
	//integrations
	@ManyToOne
	@JoinColumn(name="uom_id_fk_col")
	private Uom uom;//HAS-A
	
	@ManyToOne
	@JoinColumn(name="om_id_fk_col")
	private OrderMethod om;//HAS-A
}
