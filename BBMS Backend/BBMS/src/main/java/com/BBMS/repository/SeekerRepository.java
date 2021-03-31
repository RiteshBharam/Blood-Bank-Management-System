package com.BBMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.BBMS.model.Seeker;

public interface SeekerRepository extends JpaRepository<Seeker, Long> {
	
	@Query(value = "select count(*),blood_group,blood_bank_name,blood_bank_address from "
			+ "(select * from blood_details bd, donor d, blood_bank bb, blood_bank_blood_available bbba where seeker_seeker_id is null and d.donor_id=bd.donor_id_donor_id and bbba.blood_bank_blood_bank_id=bb.blood_bank_id and bbba.blood_available_blood_id=bd.blood_id and blood_group=?1 and expired=false)"
			+ "as tmp group by blood_bank_id ", nativeQuery = true)
	public List<String> seekBlood(int bloodType);

	
	@Query(value = "select blood_id, blood_bank_name, donor_id, name, 1 from "+
	"(select * from blood_details bd, donor d, blood_bank bb, blood_bank_blood_available bbba where seeker_seeker_id is null and d.donor_id=bd.donor_id_donor_id and bbba.blood_bank_blood_bank_id=bb.blood_bank_id and bbba.blood_available_blood_id=bd.blood_id and blood_group=?1 and expired=false)"+
	"as tmp order by blood_id asc limit 1",nativeQuery = true)
	public String requestBlood(int bloodType);

	@Query("select s from Seeker s where s.requestStatus=0")
	public List<Seeker> findAllValid(); 
	
	@Query("select s from Seeker s where s.requestStatus!=0")
	public List<Seeker> findAllInvalid(); 
}
