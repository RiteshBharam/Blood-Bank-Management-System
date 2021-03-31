package com.BBMS.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.BBMS.model.BloodBank;
import com.BBMS.model.BloodDetails;
import com.BBMS.model.Donor;
import com.BBMS.repository.BloodBankRepository;
import com.BBMS.repository.BloodDetailsRepository;
import com.BBMS.repository.DonorRepository;
import com.BBMS.services.IDonorService;
@CrossOrigin(origins="*")
@Service
public class DonorService implements IDonorService {

	@Autowired
	private DonorRepository repository;

	@Autowired
	private BloodBankRepository bloodBankRepository;

	@Autowired
	private BloodDetailsRepository bloodDetailsRepository;

	@Override
	public Donor register(Donor donor) {
		return repository.save(donor);
	}

	
	public Donor login(String email,String password) {
		Donor data = repository.loginDonor(email,password);
		return data;
	}

	@Override
	public Donor update(Long donorId, Donor donor) {
		Optional<Donor> data = repository.findById(donorId);
		if (data.isPresent()) {
			System.out.println("-------------------------------------");
			Donor save = repository.save(getDonor(donorId,donor, data));
			return save;
		}
		return null;
	}

	private Donor getDonor(Long donorId,Donor donor, Optional<Donor> data) {
		Donor holderEntity = data.get();
		holderEntity.setDonorId(donorId);
		holderEntity.setAddress(donor.getAddress());
		holderEntity.setBloodGroup(donor.getBloodGroup());
		holderEntity.setEmail(donor.getEmail());
		holderEntity.setName(donor.getName());
		holderEntity.setContactNumber(donor.getContactNumber());
		holderEntity.setPassword(donor.getPassword());
		holderEntity.setGender(donor.getGender());
		holderEntity.setAge(donor.getAge());
		return holderEntity;
	}

	public Donor getDonor(Long id) {
		Optional<Donor> findById = repository.findById(id);
		if (findById.isPresent())
			return repository.findById(id).get();
		return null;
	}

	public BloodDetails donate(Long donorId, Long bloodBankId) {
		Optional<Donor> donor = repository.findById(donorId);
		Optional<BloodBank> bloodBank = bloodBankRepository.findById(bloodBankId);
		if (bloodBank.isPresent())
			if (donor.isPresent()) {
				Donor donor2 = donor.get();
				List<BloodBank> bloodBanks = donor2.getBloodBank();
				BloodBank bloodBank2 = bloodBank.get();
				if (bloodBanks.contains(bloodBank2)) {
					bloodBanks.add(bloodBank2);
					donor2.setBloodBank(bloodBanks);
					repository.save(donor2);
				}
				List<BloodDetails> bloodAvailable = bloodBank2.getBloodAvailable();
				BloodDetails bloodDetails = new BloodDetails(new Date(), new Date(), false, donor2);
				bloodAvailable.add(bloodDetails);			
				bloodDetailsRepository.save(bloodDetails);
				bloodBankRepository.save(bloodBank2);
				return bloodDetails;
			}
		return null;
	}

	public List<Donor> getDonors() {
		return repository.findAll();
	}
}

