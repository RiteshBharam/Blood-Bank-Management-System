package com.BBMS.model.data;

import com.BBMS.model.BloodDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BloodRequestData {
	private Long bloodId;
	private String bloodBankName; 
	private Long donorId;
	private String donorName;
	private int quantity;
}