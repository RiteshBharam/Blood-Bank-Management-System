package com.BBMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.BBMS.model.BloodDetails;

@Repository
public interface BloodDetailsRepository extends JpaRepository<BloodDetails, Long> {

	@Query(value = "select  blood_id,expiration_date,expired,received_date,donor_id_donor_id,seeker_seeker_id from "
			+ "(select * from blood_details bd, donor d, blood_bank bb, blood_bank_blood_available bbba where seeker_seeker_id is null and d.donor_id=bd.donor_id_donor_id and bbba.blood_bank_blood_bank_id=bb.blood_bank_id and bbba.blood_available_blood_id=bd.blood_id and blood_group=?1 and expired=false)"
			+ "as tmp group by blood_bank_id ", nativeQuery = true)
	public BloodDetails allocateBlood(int bloodType);
}
