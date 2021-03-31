package com.BBMS.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BBMS.model.Seeker;
import com.BBMS.model.data.BloodRequestData;
import com.BBMS.services.impl.SeekerService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/admin")
public class AdminController {

		
	
	@Autowired
	private SeekerService seekerService;


	@PostMapping("/request")
	public ResponseEntity<Object> approve(@Valid @RequestBody Seeker seeker){
		List<BloodRequestData> requestBlood = seekerService.requestBlood(seeker, seeker.getBloodGroup());
		if(requestBlood!=null)
			return new ResponseEntity<>(requestBlood,HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/reject/{seekerId}")
	public ResponseEntity<Object> reject(@PathVariable String seekerId){
		Seeker seeker = seekerService.rejectBlood(Long.parseLong(seekerId));
		if(seeker!=null)
			return new ResponseEntity<>(seeker,HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/accept/{seekerId}")
	public ResponseEntity<Object> accept(@PathVariable String seekerId){
		Seeker seeker = seekerService.acceptBlood(Long.parseLong(seekerId));
		if(seeker!=null)
			return new ResponseEntity<>(seeker,HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
