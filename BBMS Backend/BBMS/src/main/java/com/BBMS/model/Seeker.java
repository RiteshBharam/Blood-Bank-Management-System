package com.BBMS.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.BBMS.model.enums.BloodType;
import com.BBMS.model.enums.RequestStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Seeker {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seekerId;

	@OneToOne(targetEntity = BloodBank.class)
	@JsonIgnore
	private BloodBank bloodBank;
	
	@NotEmpty(message = "name can not be empty")
	private String name;
	@Email(message = "invalid email")
	private String email;
	@Size(min = 10,max=10)
	private String contactNumber;
	private String address;
	@NotEmpty(message="reason can not be empty")
	private String reason;
	@NotNull
	private BloodType bloodGroup;
	private int quantity;
	private Date dateRequested=new Date();
	private RequestStatus requestStatus=RequestStatus.NEW;
}
