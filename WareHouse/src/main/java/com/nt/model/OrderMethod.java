package com.nt.model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="order_method_tab")
public class OrderMethod {

	@Id
	@GeneratedValue(generator = "om_gen")
	@SequenceGenerator(name="om_gen" ,sequenceName = "om_sql_gen")
	@Column(name="om_id_col")
	private Integer id;
	
	@Column(name="om_mode_col")
	private String orderMode;
	
	@Column(name="om_code_col")
	private String orderCode;
	
	@Column(name="om_type_col")
	private String orderType;
	
	@Column(name="om_desc_col") 
	private String orderDesc;
	
	@ElementCollection
	@CollectionTable(name="om_acpt_tab",// child table name
	                        joinColumns = @JoinColumn(name="om_id_col")//pk column name
	                        )
	@Column(name="om_acpt_col")// data column name
	private Set<String> orderAcpt;



	
}
