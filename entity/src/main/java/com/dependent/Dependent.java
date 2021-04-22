package com.dependent;

import java.util.Date;

import javax.persistence.*;

import com.enrollee.Enrollee;
import com.enrollee.Enrollee.Gender;
@Entity
@Table(name="tblDependent", schema = "spring-db")
public class Dependent {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int dependentID;

	@Column(name="dependent_name", length=50, nullable=false, unique=false)
	private String name;
	private Date birthDate;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@ManyToOne(optional = false,cascade = CascadeType.ALL)
	@JoinColumn(name = "enrolleeID", referencedColumnName = "enrolleeID")
	private Enrollee enrollee;
	
	public Dependent() {
		
	}
	
	public Dependent(int dependentID, String name, Date birthDate, Gender gender, Enrollee enrollee) {
		super();
		this.dependentID = dependentID;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.enrollee = enrollee;
	}

	public int getDependentID() {
		return dependentID;
	}

	public void setDependentID(int dependentID) {
		this.dependentID = dependentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Enrollee getEnrollee() {
		return enrollee;
	}

	public void setEnrollee(Enrollee enrollee) {
		this.enrollee = enrollee;
	}	
	
	
}
