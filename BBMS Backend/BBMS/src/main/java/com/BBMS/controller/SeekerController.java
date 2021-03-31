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

import com.BBMS.model.Seeker;
import com.BBMS.model.data.BloodBankData;
import com.BBMS.model.data.BloodRequestData;
import com.BBMS.model.enums.BloodType;
import com.BBMS.services.impl.SeekerService;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/request")
public class SeekerController {

	@Autowired
	private SeekerService service;

	@GetMapping("/sample")
	public ResponseEntity<Object> sample() {
		return new ResponseEntity<>(new Seeker(), HttpStatus.OK);

	}
	@GetMapping("/{seekerId}")
	public ResponseEntity<Object> getRequest(@PathVariable Long seekerId) {
		return new ResponseEntity<>(service.getSeeker(seekerId), HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<Object> allRequests(){
		List<Seeker> requests = service.getAllRequests();
		if(!requests.isEmpty())
			return new ResponseEntity<>(requests, HttpStatus.OK);
		return new ResponseEntity<>(new Seeker(), HttpStatus.NOT_FOUND);
	}
	@GetMapping("/history")
	public ResponseEntity<Object> allHistory(){
		List<Seeker> requests = service.getAllInvalidRequests();
		if(!requests.isEmpty())
			return new ResponseEntity<>(requests, HttpStatus.OK);
		return new ResponseEntity<>(new Seeker(), HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/inventory/{bloodType}")
	public ResponseEntity<Object> seekBlood(@PathVariable BloodType bloodType) {
		List<BloodBankData> seekBlood = service.seekBlood(bloodType);
		if (!seekBlood.isEmpty())
			return new ResponseEntity<>(seekBlood, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/blood/{bloodType}")
	public ResponseEntity<Object> requestBlood(@Valid @RequestBody Seeker seeker,@PathVariable BloodType bloodType) {
		List<BloodRequestData> seekBlood = service.requestBlood(seeker,bloodType);
		System.out.println(seekBlood);
		if (!seekBlood.isEmpty())
			return new ResponseEntity<>(seekBlood, HttpStatus.OK);
		return new ResponseEntity<>("Blood not available",HttpStatus.NOT_FOUND);
	}
}
