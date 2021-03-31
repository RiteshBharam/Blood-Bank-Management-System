package com.BBMS.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BBMS.model.BloodBank;
import com.BBMS.model.data.BloodBankData;
import com.BBMS.model.enums.BloodType;
import com.BBMS.repository.BloodBankRepository;
import com.BBMS.services.IBloodBankService;

@Service
public class BloodBankService implements IBloodBankService {

	@Autowired
	private BloodBankRepository repository;

	@Override
	public BloodBankData bloodAvailability(Long bloodBankId, BloodType bloodType) {
		String seperator = ",";
		String bloodCount = repository.getAvailability(bloodBankId, bloodType.ordinal());
		String[] strings = bloodCount.split(seperator);
		BloodBankData data = new BloodBankData(Integer.parseInt(strings[0]),
				BloodType.values()[Integer.parseInt(strings[1])], strings[2], strings[3]);
		return data;
	}

	@Override
	public List<BloodBank> getBloodBanks() {
		List<BloodBank> bloodBank = repository.findAll();
		if (bloodBank != null)
			return bloodBank;
		return null;
	}

	@Override
	public BloodBank getBloodBank(Long bloodBankId) {
		Optional<BloodBank> bloodBank = repository.findById(bloodBankId);
		if (bloodBank.isPresent())
			return bloodBank.get();
		return null;
	}

	@Override
	public BloodBank register(BloodBank bloodBank) {
		return repository.save(bloodBank);

	}

	@Override
	public BloodBank update(Long bloodBankId, BloodBank bloodBank) {
		Optional<BloodBank> data = repository.findById(bloodBank.getBloodBankId());
		if (data.isPresent()) {
			BloodBank bloodBankData = repository.save(bloodBank);
			return bloodBankData;

		}
		return null;

	}

	@Override
	public List<BloodBankData> inventory() {
		String seperator = ",";
		List<BloodBankData> details = new ArrayList<>();
		List<BloodBank> bloodBanks = repository.findAllValid();
		for (BloodBank bloodBank : bloodBanks) {
			List<String> bloodCount = repository.getBloodCount(bloodBank.getBloodBankId());
			for (String string : bloodCount) {
				String[] strings = string.split(seperator);
				BloodBankData data = new BloodBankData(Integer.parseInt(strings[0]),
						BloodType.values()[Integer.parseInt(strings[1])], strings[2], strings[3]);
				details.add(data);
			}
		}
		return details;
	}

	@Override

	public List<BloodBankData> getBloodCount(Long bloodBankId) {
		String seperator = ",";
		List<String> bloodCount = repository.getBloodCount(bloodBankId);
		List<BloodBankData> details = new ArrayList<>();
		for (String string : bloodCount) {
			String[] strings = string.split(seperator);
			BloodBankData data = new BloodBankData(Integer.parseInt(strings[0]),
					BloodType.values()[Integer.parseInt(strings[1])], strings[2], strings[3]);
			details.add(data);
		}
		return details;
	}

	@Override
	public BloodBankData inventoryBlood(BloodType bloodType) {
		String seperator = ",";
		String bloodCount = repository.getBloodCountAll(bloodType.ordinal());
		
		System.out.println(bloodCount);
		String[] strings=new String[1];
		if(bloodCount!=null)
		strings = bloodCount.split(seperator);
		BloodBankData data=new BloodBankData();
		if (strings != null)
			data = new BloodBankData(Integer.parseInt(strings[0]), BloodType.values()[Integer.parseInt(strings[1])],
					strings[2], strings[3]);
		return data;
	}

}
