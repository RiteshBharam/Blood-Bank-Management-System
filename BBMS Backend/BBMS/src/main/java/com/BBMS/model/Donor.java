package com.BBMS.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.BBMS.model.enums.BloodType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@CrossOrigin(origins="*")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Validated
public class Donor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long donorId;

	@OneToMany(targetEntity = BloodBank.class)
	@JsonIgnore
	private List<BloodBank> bloodBank;

	@NotEmpty(message = "name is empty")
	private String name;
	@Email(message = "email invalid")
	private String email;
	private String password;
	@Size(max = 10, min = 10, message = "contact number invalid")
	private String contactNumber;
	private String address;
	@NotNull
	private BloodType bloodGroup;
	private String gender;
	@Min(value = 18L)
	@Max(value = 70L)
	private int age;

}
