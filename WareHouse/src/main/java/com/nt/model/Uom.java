package com.nt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="uom_tab")
public class Uom {
	
	@Id
	@GeneratedValue(generator = "uom_gen")
	@SequenceGenerator(name = "uom_gen", sequenceName = "uom_seq")
	@Column(name="uom_id_col")
	private Integer id;
	
	@Column(name="uom_type_col",
			nullable = false,
			length = 20)
	private String uomType;
	
	@Column(name="uom_model_col",
			nullable = false,
			length = 20,
			unique = true)
	private String uomModel;
	
	@Column(name="uom_desc_col",
			nullable = false,
			length = 220)
	private String uomDesc;

}
