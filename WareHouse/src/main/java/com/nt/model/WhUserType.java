package com.nt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data 
@Entity
@Table(name="wh_user_type_tab")
public class WhUserType {
	

	@Id
	@Column(name="wh_usr_id_col")
	@GeneratedValue(generator = "whusr_gen")
	@SequenceGenerator(name = "whusr_gen", sequenceName = "whgen_seq")
	private Integer id;
	
	@Column(name="wh_usr_type_col",
			nullable = false,
			length = 20)
	private String userType;
	
	@Column(name="wh_usr_code_col",
			nullable = false,
			length = 20,
			unique = true)
	private String userCode;
	
	@Column(name="wh_usr_for_col",
			nullable = false,
			length = 20)
	private String userFor;
	
	@Column(name="wh_usr_email_col",
			nullable = false,
			length = 120,
			unique = true)
	private String userEmail;
	
	@Column(name="wh_usr_contact_col",
			nullable = false,
			length = 20)
	private String userContact;
	
	@Column(name="wh_usr_id_type_col",
			nullable = false,
			length = 20)
	private String userIdType;
	
	@Column(name="wh_usr_other_col",
			nullable = false,
			length = 20)
	private String ifOther;
	
	@Column(name="wh_usr_id_num_col",
			nullable = false,
			length = 20,
			unique = true)
	private String userIdNum;
	

}
