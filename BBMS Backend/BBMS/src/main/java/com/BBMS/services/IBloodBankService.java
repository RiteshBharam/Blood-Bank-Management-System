package com.BBMS.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.BBMS.model.BloodBank;
import com.BBMS.model.data.BloodBankData;
import com.BBMS.model.enums.BloodType;

@Service
public interface IBloodBankService {
	public BloodBank getBloodBank(Long bloodBankId);
	public BloodBank register(BloodBank bloodBank);
	public BloodBank update(Long bloodBankId,BloodBank bloodBank);
	public List<BloodBankData> inventory();
	public List<BloodBankData> getBloodCount(Long bloodBankId);
	public BloodBankData bloodAvailability(Long bloodBankId, BloodType bloodType);
	List<BloodBank> getBloodBanks();
	BloodBankData inventoryBlood(BloodType bloodType);
}
