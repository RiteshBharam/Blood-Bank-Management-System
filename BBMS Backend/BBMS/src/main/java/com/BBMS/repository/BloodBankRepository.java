package com.BBMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.BBMS.model.BloodBank;

/*
 * select tmp.blood_group as blood_group,count(*) as count from 
(select * from blood_details bd, donor d, blood_bank bb, blood_bank_blood_available bbba 
where d.donor_id=bd.donor_id_donor_id and bbba.blood_bank_blood_bank_id=bb.blood_bank_id 
and bbba.blood_available_blood_id=bd.blood_id and bb.blood_bank_id=2) 
as tmp group by blood_group;

*/
@Repository
public interface BloodBankRepository extends JpaRepository<BloodBank, Long> {
	@Query(value = "select count(*),blood_group,blood_bank_name,blood_bank_address as count from "
			+ "(select * from blood_details bd, donor d, blood_bank bb, blood_bank_blood_available bbba where d.donor_id=bd.donor_id_donor_id and bbba.blood_bank_blood_bank_id=bb.blood_bank_id and bbba.blood_available_blood_id=bd.blood_id and bb.blood_bank_id=?1 and seeker_seeker_id is null and expired=false) "
			+ "as tmp group by blood_group", nativeQuery = true)

	public List<String> getBloodCount(Long bloodBankId);
	
	@Query(value = "select count(*),blood_group,blood_bank_name,blood_bank_address from "
			+ "(select * from blood_details bd, donor d, blood_bank bb, blood_bank_blood_available bbba where d.donor_id=bd.donor_id_donor_id and bbba.blood_bank_blood_bank_id=bb.blood_bank_id and bbba.blood_available_blood_id=bd.blood_id) "
			+ "as tmp where blood_bank_id=?1 and blood_group=?2 and seeker_seeker_id is null and expired=false", nativeQuery = true)
	
	public String getAvailability(Long bloodBankId,int bloodType);
	
	
	@Query("select bb from BloodBank bb where bb.bloodBankName=?1")
	public BloodBank findByBloodBankName(String bloodBankName);
	
	
	@Query(value = "select count(*),blood_group,blood_bank_name,blood_bank_address as count from "
			+ "(select * from blood_details bd, donor d, blood_bank bb, blood_bank_blood_available bbba where d.donor_id=bd.donor_id_donor_id and bbba.blood_bank_blood_bank_id=bb.blood_bank_id and bbba.blood_available_blood_id=bd.blood_id and seeker_seeker_id is null and expired=false) "
			+ "as tmp group by blood_group having blood_group=?1", nativeQuery = true)
	public String getBloodCountAll(int bloodType);
	
	
	@Query(value = "select count(*),blood_group,blood_bank_name,blood_bank_address as count from "
			+ "(select * from blood_details bd, donor d, blood_bank bb, blood_bank_blood_available bbba where d.donor_id=bd.donor_id_donor_id and bbba.blood_bank_blood_bank_id=bb.blood_bank_id and bbba.blood_available_blood_id=bd.blood_id and seeker_seeker_id is null and expired=false) "
			+ "as tmp group by blood_group",nativeQuery = true)
	public List<BloodBank> findAllValid();
}
