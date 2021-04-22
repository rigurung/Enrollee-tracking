package com.enrollee;

import java.util.Date;
import com.dependent.*;
import javax.persistence.*;

import java.util.List;


@Entity
@Table(name="tblEnrollee", schema = "spring-db")
public class Enrollee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int enrolleeID;

	@Column(name="enrollee_name", length=50, nullable=false, unique=false)
	private String name;
	
	private Boolean activationStatus;
	private Date birthDate;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="eID", referencedColumnName = "enrolleeID") 
	//@Transient //  the Dependent table must have a foreign key column eID that references the Enrollee table's enrolleeID column.
	private List<Dependent> dependents;

	//	@Transient -> Transient means  the field will not be persisted. For instance, we can calculate the age of a student from the date of birth.
	//	private int age;

	public Enrollee() {

	}

	public Enrollee(int enrolleeID, String name, Boolean activationStatus, Date birthDate) {
		super();
		this.enrolleeID = enrolleeID;
		this.name = name;
		this.activationStatus = activationStatus;
		this.birthDate = birthDate;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getActivationStatus() {
		return activationStatus;
	}
	public void setActivationStatus(Boolean activationStatus) {
		this.activationStatus = activationStatus;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	
	public int getEnrolleeID() {
		return enrolleeID;
	}

	public void setEnrolleeID(int enrolleeID) {
		this.enrolleeID = enrolleeID;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	

	public List<Dependent> getDependents() {
		return dependents;
	}

	public void setDependents(List<Dependent> dependents) {
		this.dependents = dependents;
	}


	public enum Gender {
		MALE, 
		FEMALE
	}

}

