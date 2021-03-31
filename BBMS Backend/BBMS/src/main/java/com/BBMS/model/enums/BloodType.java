package com.BBMS.model.enums;

public enum BloodType {
	APOSITIVE("A+"),BPOSITIVE("B+"),ABPOSITIVE("AB+"),OPOSITIVE("O+"),
	ANEGATIVE("A-"),BNEGATIVE("B-"),ABNEGATIVE("AB-"),ONEGATIVE("O-");
	private String bloodType;
	private BloodType(String bloodType) {
		this.bloodType=bloodType;
	}
	public static BloodType getEnumName(int i) {
		return BloodType.values()[i];
		
	}
	
//	public static boolean isValidBloodType(String bloodType) {
//		return BloodType.valueOf(BloodType.class,bloodType);
//	}
}
