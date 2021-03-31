package com.BBMS.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BloodBank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bloodBankId;

	@NotEmpty(message = "blood bank name is empty")
	private String bloodBankName;

	@NotEmpty(message = "blood bank address should not be empty")
	private String bloodBankAddress;
	
	public BloodBank(String bloodBankName,String bloodBankAddress) {
		this.bloodBankName=bloodBankName;
		this.bloodBankAddress=bloodBankAddress;
	}
	
	@ManyToMany(targetEntity = BloodDetails.class)
	@JsonIgnore
	private List<BloodDetails> bloodAvailable;
}
