package com.BBMS.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.BBMS.model.Donor;

public interface DonorRepository extends JpaRepository<Donor, Long>{

	@Query("select d from Donor d where d.email=?1 and d.password=?2")
	public Donor loginDonor(String email,String password);
	
	
	@Query("select d from Donor d,BloodDetails bd where d.donorId=bd.donorId and bd.bloodId=?1")
	public Donor getDonorBloodData(Long bloodDetailsId);
	
	
}
