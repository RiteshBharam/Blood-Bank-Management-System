package com.BBMS.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BloodDetails {

	public BloodDetails(Date receivedDate, Date expirationDate, boolean expired, Donor donorId) {
		this.receivedDate=receivedDate;
		this.expirationDate=expirationDate;
		this.donorId=donorId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bloodId;

	private Date receivedDate;

	private Date expirationDate;
	
	private boolean expired;
	
	@ManyToOne(targetEntity = Donor.class)
	@NotNull
	private Donor donorId;
	
	@ManyToOne(targetEntity = Seeker.class)
	@JsonIgnore
	private Seeker seeker;
	
}
