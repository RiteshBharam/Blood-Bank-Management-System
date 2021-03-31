package com.BBMS.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BBMS.model.BloodBank;
import com.BBMS.model.data.BloodBankData;
import com.BBMS.model.enums.BloodType;
import com.BBMS.services.impl.BloodBankService;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/bloodbank")
public class BloodBankController {

	@Autowired
	private BloodBankService bloodBankService;

	@GetMapping("/sample")
	public ResponseEntity<Object> getSample() {
		return new ResponseEntity<>(new BloodBank(), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Object> getBloodBanks() {
		List<BloodBank> data = bloodBankService.getBloodBanks();
		if (data != null) {
			return new ResponseEntity<>(data, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{bloodBankId}")
	public ResponseEntity<Object> getBloodBank(@PathVariable Long bloodBankId) {
		BloodBank data = bloodBankService.getBloodBank(bloodBankId);
		if (data != null) {
			return new ResponseEntity<>(data, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/inventory")
	public ResponseEntity<Object> getInventory() {
		List<BloodBankData> data = bloodBankService.inventory();
		if (!data.isEmpty()) {
			return new ResponseEntity<>(data, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/inventory/blood/{bloodType}")
	public ResponseEntity<Object> getInventoryBlood(BloodType bloodType) {
		BloodBankData data = bloodBankService.inventoryBlood(bloodType);
		if (data != null) {
			return new ResponseEntity<>(data, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/inventory/{bloodBankId}")
	public ResponseEntity<Object> getInventory(@PathVariable Long bloodBankId) {
		List<BloodBankData> data = bloodBankService.getBloodCount(bloodBankId);
		if (!data.isEmpty()) {
			return new ResponseEntity<>(data, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/register")
	public ResponseEntity<Object> register(@Valid @RequestBody BloodBank bloodBank) {
		BloodBank register = bloodBankService.register(bloodBank);
		if (register != null)
			return new ResponseEntity<>(register, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/availability/{bloodBankId}/blood/{bloodType}")
	public ResponseEntity<Object> availability(@PathVariable Long bloodBankId, @PathVariable BloodType bloodType) {
		BloodBankData data = bloodBankService.bloodAvailability(bloodBankId, bloodType);
		if (data != null)
			return new ResponseEntity<>(data, HttpStatus.OK);
		return new ResponseEntity<>("Blood Not Available", HttpStatus.NOT_FOUND);
	}

}
