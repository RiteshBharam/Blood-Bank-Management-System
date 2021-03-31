package com.BBMS.model.data;

import java.io.Serializable;

import com.BBMS.model.enums.BloodType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class BloodBankData implements Serializable {
	
	private int count;
	private BloodType bloodType;
	private String bloodBankName;
	private String bloodBankAddress;
	
}
