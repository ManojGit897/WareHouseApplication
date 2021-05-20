package com.nt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="shipment_type_tab")
@Data
public class ShipmentType{
	
	@Id
	@GeneratedValue(generator = "ship_type_gen")
	@SequenceGenerator(name = "ship_type_gen" , sequenceName = "ship_type_seq")
	@Column(name="ship_id_col")
	private Integer id;
	
	@Column(name="ship_mode_col")
	private String shipMode;
	
	@Column(name="ship_code_col")
	private String shipCode;
	
	@Column(name="ship_enable_col")
	private String enableShip;
	
	@Column(name="ship_grade_col")
	private String shipGrade;
	
	@Column(name="ship_desc_col")
	private String shipDesc;

}
