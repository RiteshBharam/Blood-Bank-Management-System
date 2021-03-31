package com.BBMS.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BBMS.model.BloodDetails;
import com.BBMS.model.Seeker;
import com.BBMS.model.data.BloodBankData;
import com.BBMS.model.data.BloodRequestData;
import com.BBMS.model.enums.BloodType;
import com.BBMS.model.enums.RequestStatus;
import com.BBMS.repository.BloodDetailsRepository;
import com.BBMS.repository.SeekerRepository;
import com.BBMS.services.ISeekerService;

@Service
public class SeekerService implements ISeekerService {

	@Autowired
	private SeekerRepository seekerRepository;

	@Autowired
	private BloodDetailsRepository bloodDetailsRepository;

	@Override
	public List<BloodBankData> seekBlood(BloodType bloodType) {

		String seperator = ",";
		List<String> details = seekerRepository.seekBlood(bloodType.ordinal());
		List<BloodBankData> dataSet = new ArrayList<>();
		for (String bloodData : details) {
			System.out.println(bloodData);
			String[] strings = bloodData.split(seperator);
			BloodBankData bankData = new BloodBankData(Integer.parseInt(strings[0]),
					BloodType.values()[Integer.parseInt(strings[1])], strings[2], strings[3]);
			dataSet.add(bankData);
		}
		return dataSet;
	}

	@Override
	public Seeker seekerExists(Long seekerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seeker getSeeker(Long seekerId) {
		// TODO Auto-generated method stub
		return seekerRepository.findById(seekerId).get();
	}

	@Override
	public List<BloodRequestData> requestBlood(Seeker seeker, BloodType bloodType) {
		List<BloodRequestData> data = new ArrayList<>();
		int quantity = seeker.getQuantity();
		// seeker.setRequestStatus(RequestStatus.ACCEPTED);
		seekerRepository.save(seeker);
		for (int i = 0; i < quantity; i++) {
			String data1 = seekerRepository.requestBlood(bloodType.ordinal());
			if (data1 != null) {
				String[] requestBlood = data1.split(",");
				long bloodId = Long.parseLong(requestBlood[0]);
				BloodRequestData requestData = new BloodRequestData(bloodId, requestBlood[1],
						Long.parseLong(requestBlood[2]), requestBlood[3], Integer.parseInt(requestBlood[4]));
				// BloodDetails bloodDetails = bloodDetailsRepository.findById(bloodId).get();
				// bloodDetails.setSeeker(seeker);
				// bloodDetailsRepository.save(bloodDetails);
				data.add(requestData);
			}
		}
		return data;
	}

	@Override
	public Seeker rejectBlood(Long seekerId) {
		Optional<Seeker> seekerOptional = seekerRepository.findById(seekerId);
		if (seekerOptional.isPresent()) {
			Seeker seeker = seekerOptional.get();
			seeker.setRequestStatus(RequestStatus.REJECTED);
			seekerRepository.save(seeker);
			return seeker;
		}
		return null;

	}

	public List<Seeker> getAllRequests() {
		List<Seeker> seekers = seekerRepository.findAllValid();
		//System.out.println(seekers);
		if (!seekers.isEmpty()) {
			return seekers;
		}
		return null;
	}
	
	public List<Seeker> getAllInvalidRequests() {
		List<Seeker> seekers = seekerRepository.findAllInvalid();
		System.out.println(seekers);
		if (!seekers.isEmpty()) {
			return seekers;
		}
		return null;
	}

	public Seeker acceptBlood(Long seekerId) {
		Optional<Seeker> seekerOptional = seekerRepository.findById(seekerId);
		if (seekerOptional.isPresent()) {
			Seeker seeker = seekerOptional.get();
			List<BloodRequestData> bloodData = requestBlood(seeker, seeker.getBloodGroup());
			System.out.println(bloodData);
			for(BloodRequestData data:bloodData) {
				System.out.println("allocating blood "+data);
				Long bloodId = data.getBloodId();
				BloodDetails blood=bloodDetailsRepository.allocateBlood(seeker.getBloodGroup().ordinal());
				blood.setSeeker(seeker);
				bloodDetailsRepository.save(blood);
			}
			seeker.setRequestStatus(RequestStatus.ACCEPTED);
			seekerRepository.save(seeker);
			return seeker;
		}
		return null;
	}
}
